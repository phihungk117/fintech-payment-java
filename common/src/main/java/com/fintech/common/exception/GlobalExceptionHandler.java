package com.fintech.common.exception;

import com.fintech.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

@Slf4j // Sử dụng Lombok để tạo logger
@RestControllerAdvice
public class GlobalExceptionHandler{
    //1 bắt lỗi logic nghiệp vụ chung (400)
    @ExceptionHandler(BusinessException.class)
    //ResponseEntity<ApiResponse<Void>>  kiểu dữ liệu trả về cho client
    //ResponseEntity: chứa HTTP status (400, 404...)
    //ApiResponse: format JSON chuẩn của bạn
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex){
        log.warn("Business Exception:{}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(ex.getMessage(),ex.getErrorCode()));
    }
    // 2. Bắt lỗi không tìm thấy tài nguyên (404 Not Found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("Resource Not Found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage(), "NOT_FOUND"));
    }
    // 3. Bắt lỗi thiếu số dư (422 Unprocessable Entity) [cite: 90]
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiResponse<Void>> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        log.warn("Insufficient Balance: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(ex.getMessage(), "INSUFFICIENT_BALANCE"));
    }

    // 4. Bắt lỗi trùng lặp Request (409 Conflict) [cite: 91]
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateRequestException(DuplicateRequestException ex) {
        log.warn("Duplicate Request: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage(), "DUPLICATE_REQUEST"));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        // Gom tất cả các thông báo lỗi validation thành 1 chuỗi
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        log.warn("Validation Error: {}", errorMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(errorMessage, "VALIDATION_FAILED"));
    }

    // 6. Bắt mọi lỗi hệ thống (500 Internal Server Error) - Tránh lộ trace code ra ngoài [cite: 93]
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        log.error("Internal Server Error: ", ex); // Log chi tiết lỗi để backend dev debug
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Hệ thống đang gặp sự cố, vui lòng thử lại sau.", "INTERNAL_ERROR"));
    }

}

