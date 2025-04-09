package com.fetch.rewards.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Receipt {
    @Getter @Setter @NotBlank(message = "Retailer name is required")
    private String retailer;

    @Getter @Setter @NotBlank(message = "Purchase date is required")
    private String purchaseDate;

    @Getter @Setter @NotBlank(message = "Purchase time is required")
    private String purchaseTime;

    @Getter @Setter @Valid @NotEmpty(message = "Items list cannot be empty")
    private List<Item> items;

    @Getter @Setter @NotBlank(message = "Total is required")
    private String total;
}

