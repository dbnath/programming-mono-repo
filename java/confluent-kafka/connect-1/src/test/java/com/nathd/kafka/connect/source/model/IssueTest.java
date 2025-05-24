package com.nathd.kafka.connect.source.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class IssueTest {

    @Test
    public void testIssue() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Instant.class,
                        (JsonDeserializer<Instant>) (json, type, jsonDeserializationContext) ->
                                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toInstant())
                .create();

        InputStream input = IssueTest.class.getClassLoader().getResourceAsStream("data/mock-issue.json");
        Reader reader = new InputStreamReader(input);
        Issue issue = gson.fromJson(reader, Issue.class);

        assertThat(issue.getRepositoryUrl(), is(notNullValue()));
        assertEquals(issue.getUrl(), "https://api.github.com/repos/kubernetes/kubernetes/issues/87554");
        assertEquals(issue.getTitle(), "[client-go] Can't override config values");
        assertEquals(issue.getCreatedAt().toString(), "2020-01-26T13:26:42Z");
        assertEquals(issue.getUpdatedAt().toString(), "2020-01-26T18:39:32Z");
        assertEquals(issue.getNumber(), (Integer) 87554);
        assertEquals(issue.getState(), "open");

        // user
        User user = issue.getUser();
        assertEquals(user.getId(), (Integer) 582870);
        assertEquals(user.getUrl(), "https://api.github.com/users/vvelikodny");
        assertEquals(user.getHtmlUrl(), "https://github.com/vvelikodny");
        assertEquals(user.getLogin(), "vvelikodny");

        // pr
        PullRequest pullRequest = issue.getPullRequest();
        assertNotNull(pullRequest);
        assertEquals(pullRequest.getUrl(), "https://api.github.com/repos/kubernetes/kubernetes/pulls/87557");
        assertEquals(pullRequest.getHtmlUrl(), "https://github.com/kubernetes/kubernetes/pull/87557");
    }
}
