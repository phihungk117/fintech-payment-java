//Dùng khi không tìm thấy user, ví, giao dịch...
package com.fintech.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}