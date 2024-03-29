package com.api.jbcompany.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/vagas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cursos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aulas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aulas/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cursos/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/candidaturas/contar/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/candidaturas-cursos/contar/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aulas/curso/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/candidaturas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/candidaturas-cursos").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
