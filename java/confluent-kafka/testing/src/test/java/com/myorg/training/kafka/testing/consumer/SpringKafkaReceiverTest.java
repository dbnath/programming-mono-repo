package com.myorg.training.kafka.testing.consumer;

import com.myorg.training.kafka.testing.data.TransactionRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaReceiverTest {

    private static final String TOPIC = "topic";
    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule =
            new EmbeddedKafkaRule(1, true, 1, TOPIC);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testMessageProcessed() throws InterruptedException {
        long transactionId = 1;

        kafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), "{\"transactionId\": " + transactionId + ", " +
                "\"accountNumber\": \"acc1\", \"amount\": 2000 }");

        Thread.sleep(1000);
        assertThat(transactionRepository.findById(transactionId)).isNotEmpty();
    }
}
