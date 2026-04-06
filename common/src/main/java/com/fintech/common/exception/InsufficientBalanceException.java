//Dùng khi số dư ví không đủ để thanh toán/trả hoa hồng
package com.fintech.common.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}