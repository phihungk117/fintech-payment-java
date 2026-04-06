package com.fintech.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailedEvent {
    private String eventId;
    private String traceId;
    private String transactionId;
    private String errorMessage;     // Lý do lỗi
    private LocalDateTime failedAt;
}