package com.empik.recruitment.homework.config;

import com.empik.recruitment.homework.model.LoginCounter;
import com.empik.recruitment.homework.repository.LoginCounterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Log4j2
public class RestConfiguration {

    @Value("${api.users}")
    private String userRootUri;

    @Bean
    @Qualifier("userRestTemplate")
    public RestTemplate userRestTemplate(RestTemplateBuilder builder){
        return builder.rootUri(userRootUri).build();
    }

    @Bean
    CommandLineRunner initLoginCounterDB(LoginCounterRepository repository){
       return args -> {
           log.info("Init counter " + repository.save(new LoginCounter("tlpchk", 10)));
           log.info("Init counter " + repository.save(new LoginCounter("octocat", 5)));
       };
    }
}
