package com.myorg.training.kafka.testing.data;

import com.myorg.training.kafka.testing.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
