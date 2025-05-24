package com.git.amc.rules.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private CustomerType type;
    private int years;
    private int discount;

    public Customer(CustomerType type, int numOfYears) {
        this.type = type;
        this.years = numOfYears;
    }

    public enum CustomerType {
        INDIVIDUAL, BUSINESS
    }
}
