package com.nathd.kafka.connect.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.nathd.kafka.connect.source.api.GitHubApi;
import com.nathd.kafka.connect.source.model.Issue;
import org.junit.Test;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.nathd.kafka.connect.source.GitHubSourceConnectorConfig.*;
import static org.junit.Assert.*;

public class GitHubSourceTaskTest {

    private GitHubSourceTask gitHubSourceTask = new GitHubSourceTask();
    private Integer batchSize = 10;
    private Gson gson = new Gson();

    private Map<String, String> initialConfig() {
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_CONFIG, "apache");
        baseProps.put(REPO_CONFIG, "kafka");
        baseProps.put(SINCE_CONFIG, "2017-04-26T01:23:44Z");
        baseProps.put(BATCH_SIZE_CONFIG, batchSize.toString());
        baseProps.put(TOPIC_CONFIG, "github-issues");
        return baseProps;
    }


    @Test
    public void test() throws UnirestException {
        gitHubSourceTask.config = new GitHubSourceConnectorConfig(initialConfig());
        gitHubSourceTask.nextPageToVisit = 1;
        gitHubSourceTask.nextQuerySince = Instant.parse("2017-01-01T00:00:00Z");
        gitHubSourceTask.gitHubApi = new GitHubApi(gitHubSourceTask.config);
        String url = gitHubSourceTask.gitHubApi.constructUrl(gitHubSourceTask.nextPageToVisit, gitHubSourceTask.nextQuerySince);
        System.out.println(url);
        HttpResponse<String> httpResponse = gitHubSourceTask.gitHubApi.getNextIssuesResponse(gitHubSourceTask.nextPageToVisit, gitHubSourceTask.nextQuerySince);
        if (httpResponse.getStatus() != 403) {
            assertEquals(200, httpResponse.getStatus());
            Set<String> headers = httpResponse.getHeaders().keySet();
            assertTrue(headers.contains("ETag"));
            assertTrue(headers.contains("X-RateLimit-Limit"));
            assertTrue(headers.contains("X-RateLimit-Remaining"));
            assertTrue(headers.contains("X-RateLimit-Reset"));

            Type listType = new TypeToken<List<Issue>>() {
            }.getType();
            List<Issue> issues = gson.fromJson(httpResponse.getBody(), listType);

            assertEquals(batchSize.intValue(), issues.size());
            Issue issue = issues.get(0);
            assertNotNull(issue);
            assertNotNull(issue.getNumber());
            assertEquals(2072, issue.getNumber().intValue());
        }
    }
}