package com.git.amc.rules.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Cashflow {
    private Account account;
    private LocalDate date;
    private double amount;
    private FlowType flowType;

    public enum FlowType {
        CREDIT, DEBIT
    }
}
