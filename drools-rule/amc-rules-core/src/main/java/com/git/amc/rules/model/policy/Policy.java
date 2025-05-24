package com.git.amc.rules.model.policy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Policy {
    private String type = "COMPREHENSIVE";
    private boolean approved = false;
    private int discountPercent = 0;
    private int basePrice;

    public void applyDiscount(int discount) {
        this.discountPercent += discount;
    }
}
