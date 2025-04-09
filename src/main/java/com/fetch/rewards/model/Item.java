package com.fetch.rewards.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class Item {

    @Getter @Setter @NotBlank(message = "Item description is required")
    private String shortDescription;

    @Getter @Setter @NotBlank(message = "Item price is required")
    private String price;
}
