package com.myorg.training.kafka.testing.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "TRANSACTION")
public class Transaction implements Persistable {

    @Id
    @Column(name = "TRANSACTION_ID")
    @JsonProperty
    private Long transactionId;

    @JsonProperty
    @Column(name = "ACCOUNT_NUM")
    private String accountNumber;

    @JsonProperty
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Transient
    private boolean isNew;

    @Override
    public Object getId() {
        return transactionId;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}
