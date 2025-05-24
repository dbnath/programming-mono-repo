package com.git.amc.rules.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "accountNumber")
@NoArgsConstructor
@ToString
public class Account {
    private long accountNumber;
    private String accountName;
    private double balance = 0;
    private boolean isOverdraft;
    private List<String> restrictionCodes;

    public Account(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
