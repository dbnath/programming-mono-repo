package com.git.amc.rules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(metadata())
                .select()
                .paths(PathSelectors.ant("/rules/**"))
                .build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title( "AMC Rules Api" )
                .description( "APIs for evaluating business rules." )
                .version( "1.0.0" )
                .build();
    }
}
