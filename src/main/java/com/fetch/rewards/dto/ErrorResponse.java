package com.fetch.rewards.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ErrorResponse {

    @Getter @Setter
    private String message;

    @Getter @Setter
    private List<String> errors;

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
