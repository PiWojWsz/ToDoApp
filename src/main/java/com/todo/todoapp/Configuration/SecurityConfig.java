package com.todo.todoapp.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
public class SecurityConfig
{
    @Bean
    public UserDetailsService users()
    {
        UserDetails user1 = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
