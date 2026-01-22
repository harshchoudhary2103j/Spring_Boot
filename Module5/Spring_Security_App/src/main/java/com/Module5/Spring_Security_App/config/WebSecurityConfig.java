package com.Module5.Spring_Security_App.config;

import com.Module5.Spring_Security_App.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/posts","/auth/**").permitAll()
//                                .requestMatchers("/posts/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionConfig-> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());
        return  httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }
//    @Bean
//    UserDetailsService inMemoryUser(){
//        UserDetails normalUser = User
//                .withUsername("Harsh")
//                .password(passwordEncoder().encode("harsh123"))
//                .roles("User")
//                .build();
//        UserDetails adminUser = User
//                .withUsername("Admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//    }


}

