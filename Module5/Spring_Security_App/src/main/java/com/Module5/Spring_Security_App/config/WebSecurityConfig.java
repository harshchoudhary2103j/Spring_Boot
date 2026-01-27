package com.Module5.Spring_Security_App.config;

import com.Module5.Spring_Security_App.entities.enums.Permission;
import com.Module5.Spring_Security_App.filter.JwtAuthFilter;
import com.Module5.Spring_Security_App.handlers.OAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import static com.Module5.Spring_Security_App.entities.enums.UserRole.ADMIN;
import static com.Module5.Spring_Security_App.entities.enums.UserRole.CREATOR;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter, OAuth2SuccessHandler oAuth2SuccessHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
    }
    private static final String[]publicRoutes = {"/auth/**","/home.html","/error"};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers(publicRoutes).permitAll()
                                .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyRole(ADMIN.name(),CREATOR.name())
                                .requestMatchers(HttpMethod.POST,"/posts/**")
                                            .hasAnyAuthority(Permission.POST_CREATE.name())
                                .requestMatchers(HttpMethod.GET,"/posts/**")
                                .hasAuthority(Permission.POST_VIEW.name())
                                .requestMatchers(HttpMethod.DELETE,"/posts/**")
                                .hasAuthority(Permission.POST_DELETE.name())

//
                                .anyRequest().authenticated()
                )
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer.failureUrl("/login?error = true")
//                        .successHandler(oAuth2SuccessHandler)
//                );

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

