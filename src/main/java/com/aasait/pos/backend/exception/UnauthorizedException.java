package com.aasait.pos.backend.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) { super(msg); }
}

