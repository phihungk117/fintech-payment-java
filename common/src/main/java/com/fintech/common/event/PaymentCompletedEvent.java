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
public class PaymentCompletedEvent {
    private String eventId;
    private String traceId;
    private String transactionId;
    private String senderId;
    private String receiverId;
    private LocalDateTime completedAt;
}