package com.reddit.backend.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String message) {
    super(message);
    }
}
