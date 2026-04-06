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
public class WalletUpdatedEvent {
    private String eventId;
    private String traceId;
    private String walletId;
    private String userId;
    private String transactionId;
    private BigDecimal amount;       // Số tiền thay đổi
    private BigDecimal balanceAfter; // Số dư sau khi thay đổi
    private String type;             // DEBIT (trừ tiền) hoặc CREDIT (cộng tiền)
    private LocalDateTime updatedAt;
}