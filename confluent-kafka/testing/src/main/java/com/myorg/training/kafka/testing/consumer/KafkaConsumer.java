package com.myorg.training.kafka.testing.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.training.kafka.testing.data.TransactionRepository;
import com.myorg.training.kafka.testing.data.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    private final TransactionRepository transactionRepository;
    private ObjectMapper objectMapper;

    public KafkaConsumer(TransactionRepository transactionRepository, ObjectMapper objectMapper) {
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "topic", groupId = "group-id")
    public void consume(String record){
        log.info("consumer received message -{}", record);
        Transaction transaction = null;
        try {
            transaction = this.objectMapper.readValue(record, Transaction.class);
            transaction.setNew(true);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            log.error("Exception while processing consumed record. {}", e.getMessage(), e);
        }
    }
}
