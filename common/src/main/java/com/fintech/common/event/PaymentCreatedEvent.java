//payment-created-event = gói dữ liệu(message) mô tả 1 lần thanh toán
//dùng để truyền giữa các service qua Kafka, RabbitMQ hoặc Event Bus nội bộ
package com.fintech.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreatedEvent{
    private String eventId;          // UUID để check Idempotency nếu cần
    private String traceId;          // Dùng cho Zipkin tracing
    private String transactionId;
    private String senderId;
    private String receiverId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime createdAt;
}