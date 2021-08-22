package com.empik.recruitment.homework.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Value("${api.users}")
    private String userRootUri;

    @Bean
    @Qualifier("userRestTemplate")
    public RestTemplate userRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(userRootUri).build();
    }

}
