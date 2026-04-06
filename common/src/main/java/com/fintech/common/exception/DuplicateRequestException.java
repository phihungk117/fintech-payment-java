//Dùng để chặn double-click, spam request cùng 1 Idempotency Key
package com.fintech.common.exception;

public class DuplicateRequestException extends RuntimeException {
    public DuplicateRequestException(String message) {
        super(message);
    }
}