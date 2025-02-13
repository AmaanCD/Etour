package com.project.ETour.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.
                authorizeHttpRequests(auth->
                        auth.anyRequest().authenticated()
                ).oauth2ResourceServer(oauth->
                        oauth.jwt(
                                jwt->jwt.jwtAuthenticationConverter(new JwtAuthenticationConverter())
                        ));
        return httpSecurity.build();


    }

}
