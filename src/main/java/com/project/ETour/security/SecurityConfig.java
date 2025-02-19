package com.project.ETour.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final Environment environment;

    Logger logger = LoggerFactory.getLogger(getClass());


    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity ) throws Exception {
        logger.info("Security url for certs {}",environment.getProperty("spring.security.certs"));
        httpSecurity.
                authorizeHttpRequests(auth->
                        auth.anyRequest().authenticated()
                ).oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwkSetUri(environment.getProperty("spring.security.certs")))
                );
        return httpSecurity.build();


    }

}
