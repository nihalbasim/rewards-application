package com.fetch.rewards.dto;

import lombok.Getter;
import lombok.Setter;

public class ReceiptResponse {

    @Getter @Setter
    private String id;

    public ReceiptResponse(String id) {
        this.id = id;
    }
}
