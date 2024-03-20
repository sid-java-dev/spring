package com.sp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/hello").hasAnyRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("sid")
                .password(passwordEncoder().encode("test"))
                .roles("ADMIN") // Change "ROLE_ADMIN" to "ADMIN"
                .build();

        UserDetails user = User.builder()
                .username("sachin")
                .password(passwordEncoder().encode("testing"))
                .roles("USER") // Similarly, you might want to change "ROLE_USER" to "USER"
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
