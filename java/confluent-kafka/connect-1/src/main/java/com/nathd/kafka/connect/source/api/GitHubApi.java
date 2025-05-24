package com.nathd.kafka.connect.source.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.nathd.kafka.connect.source.GitHubSourceConnectorConfig;
import com.nathd.kafka.connect.source.model.Issue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.errors.ConnectException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class GitHubApi {

    private final GitHubSourceConnectorConfig config;
    private final Gson gson;
    private Integer XRateLimit = 9999;
    private Integer XRateRemaining = 9999;
    private long XRateReset = Instant.MAX.getEpochSecond();

    public GitHubApi(GitHubSourceConnectorConfig config) {
        this.config = config;
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Instant.class,
                        (JsonDeserializer<Instant>) (json, type, jsonDeserializationContext) ->
                                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toInstant()).create();
    }

    public Issue[] getNextIssues(Integer page, Instant since) throws InterruptedException {
        HttpResponse<String> response;
        try {
            response = getNextIssuesResponse(page, since);
            Headers headers = response.getHeaders();
            XRateLimit = Integer.valueOf(headers.getFirst("X-RateLimit-Limit"));
            XRateRemaining = Integer.valueOf(headers.getFirst("X-RateLimit-Remaining"));
            XRateReset = Integer.valueOf(headers.getFirst("X-RateLimit-Reset"));

            switch (response.getStatus()) {
                case 200:
                    List<Issue> issues = gson.fromJson(response.getBody(), new TypeToken<List<Issue>>() {
                    }.getType());
                    return issues.toArray(new Issue[0]);
                case 401:
                    throw new ConnectException("Bad GitHub credentials provided, please edit your config");
                case 403:
                    // we have issues too many requests.
                    log.info(String.format("Your rate limit is %s", XRateLimit));
                    log.info(String.format("Your remaining calls is %s", XRateRemaining));
                    log.info(String.format("The limit will reset at %s",
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(XRateReset), ZoneOffset.systemDefault())));
                    long sleepTime = XRateReset - Instant.now().getEpochSecond();
                    log.info(String.format("Sleeping for %s seconds", sleepTime));
                    Thread.sleep(1000 * sleepTime);
                    return getNextIssues(page, since);
                default:
                    log.error(constructUrl(page, since));
                    log.error(String.valueOf(response.getStatus()));
                    log.error(response.getBody());
                    log.error(response.getHeaders().toString());
                    log.error("Unknown error: Sleeping 5 seconds before re-trying");
                    Thread.sleep(5000L);
                    return getNextIssues(page, since);
            }

        } catch (UnirestException e) {
            log.error(e.getMessage(), e);
            Thread.sleep(5000L);
            return new Issue[0];
        }
    }


    public HttpResponse<String> getNextIssuesResponse(Integer page, Instant since) throws UnirestException {
        GetRequest uniRest = Unirest.get(constructUrl(page, since));
        if (!config.getAuthUsername().isEmpty() && !config.getAuthPassword().isEmpty()) {
            uniRest = uniRest.basicAuth(config.getAuthUsername(), config.getAuthPassword());
        }
        log.debug(String.format("GET %s", uniRest.getUrl()));
        HttpResponse<String> response = uniRest.asString();
        return uniRest.asString();
    }

    public String constructUrl(Integer page, Instant since) {
        return String.format(
                "https://api.github.com/repos/%s/%s/issues?page=%s&per_page=%s&since=%s&state=all&direction=asc&sort=updated",
                config.getOwnerConfig(),
                config.getRepoConfig(),
                page,
                config.getBatchSize(),
                since.toString());
    }

    public void sleep() throws InterruptedException {
        long sleepTime = (long) Math.ceil(
                (double) (XRateReset - Instant.now().getEpochSecond()) / XRateRemaining);
        log.debug(String.format("Sleeping for %s seconds", sleepTime));
        Thread.sleep(1000 * sleepTime);
    }

    public void sleepIfNeed() throws InterruptedException {
        // Sleep if needed
        if (XRateRemaining <= 10 && XRateRemaining > 0) {
            log.info(String.format("Approaching limit soon, you have %s requests left", XRateRemaining));
            sleep();
        }
    }
}
