package com.ll.medium.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/**")
                                .permitAll()
                )
                .headers(
                        headers ->
                                headers.frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::disable
                                )
                )
                .formLogin(
                        formLogin -> {
                            formLogin
                                    .loginPage("/member/login")
                                    .successForwardUrl("/");
                        }
                )
                .logout(
                        logout -> {
                            logout.logoutUrl("/member/logout")
                                    .logoutSuccessUrl("/");
                        }
                )
                .csrf(
                        csrf ->
                                csrf.ignoringRequestMatchers(
                                        "/h2-console/**"
                                )
                );

        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
