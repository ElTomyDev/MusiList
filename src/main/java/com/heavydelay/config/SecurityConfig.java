package com.heavydelay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Value("${admin.endpoints.enable:false}")
    private boolean adminEndpointsEnable;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                //Configuracion para los endpoints de admin
                if (adminEndpointsEnable){
                    auth.requestMatchers(new AntPathRequestMatcher("/sys/api/v1/user/admin")).permitAll();
                } else {
                    auth.requestMatchers(new AntPathRequestMatcher("/sys/api/v1/user/admin")).denyAll();
                }

                //Configuracion general para todos los demas endpoints
                auth.anyRequest().permitAll();
            })
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("112233"))
            .roles("ADMIN")
            .build();
        
            return new InMemoryUserDetailsManager(admin);
    }
}
