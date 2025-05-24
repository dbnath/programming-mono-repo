package com.nathd.kafka.connect.source;

import org.apache.kafka.common.config.ConfigDef;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.nathd.kafka.connect.source.GitHubSourceConnectorConfig.*;
import static org.junit.Assert.assertTrue;

public class GitHubSourceConnectorConfigTest {

    private ConfigDef configDef = GitHubSourceConnectorConfig.config();
    private Map<String, String> config;

    @Before
    public void setup() {
        config = new HashMap<>();
        config.put(TOPIC_CONFIG, "topic");
        config.put(OWNER_CONFIG, "owner");
        config.put(REPO_CONFIG, "repo");
        config.put(SINCE_CONFIG, "2017-04-26T01:23:45Z");
        config.put(BATCH_SIZE_CONFIG, "100");
    }

    @Test
    public void testConfigDef() {
        assertTrue(configDef.validate(config).stream().allMatch(configValue -> configValue.errorMessages().size() == 0));
    }

    @Test
    public void testValidDate() {
        config.put(SINCE_CONFIG, "not-a-valid-date");
        assertTrue(configDef.validateAll(config).get(SINCE_CONFIG).errorMessages().size() > 0);
    }

    @Test
    public void testBatchSize() {
        config.put(BATCH_SIZE_CONFIG, "-1");
        assertTrue(configDef.validateAll(config).get(BATCH_SIZE_CONFIG).errorMessages().size() > 0);

        config.put(BATCH_SIZE_CONFIG, "101");
        assertTrue(configDef.validateAll(config).get(BATCH_SIZE_CONFIG).errorMessages().size() > 0);
    }

}
