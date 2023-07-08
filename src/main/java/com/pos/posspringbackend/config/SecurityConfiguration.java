package com.pos.posspringbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.pos.posspringbackend.user.enumerated.Permission.*;
import static com.pos.posspringbackend.user.enumerated.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**")
                .permitAll()
                .antMatchers("/api/v1/products/**").hasAnyRole(ADMIN.name(), INVENTORY_CLERK.name())
                .antMatchers(GET,"/api/v1/products/**").hasAnyAuthority(ADMIN_READ.name(), INVENTORY_CLERK_READ.name())
                .antMatchers(POST,"/api/v1/products/**").hasAnyAuthority(ADMIN_CREATE.name(), INVENTORY_CLERK_CREATE.name())
                .antMatchers(PUT,"/api/v1/products/**").hasAnyAuthority(ADMIN_UPDATE.name(), INVENTORY_CLERK_UPDATE.name())
                .antMatchers(DELETE,"/api/v1/products/**").hasAnyAuthority(ADMIN_DELETE.name())
                .antMatchers("/api/v1/sales/**").hasAnyRole(ADMIN.name(), CASHIER.name())
                .antMatchers(GET,"/api/v1/sales/**").hasAnyAuthority(ADMIN_READ.name(), CASHIER_READ.name())
                .antMatchers(POST,"/api/v1/sales/**").hasAnyAuthority(ADMIN_CREATE.name(), CASHIER_CREATE.name())
                .antMatchers(PUT,"/api/v1/sales/**").hasAnyAuthority(ADMIN_UPDATE.name(), CASHIER_UPDATE.name())
                .antMatchers(DELETE,"/api/v1/sales/**").hasAnyAuthority(ADMIN_DELETE.name())
                .antMatchers("/api/v1/products/**").hasAnyRole(ADMIN.name(), INVENTORY_CLERK.name())
                .antMatchers(GET,"/api/v1/products/**").hasAnyAuthority(ADMIN_READ.name(), INVENTORY_CLERK_READ.name())
                .antMatchers(POST,"/api/v1/products/**").hasAnyAuthority(ADMIN_READ.name(), INVENTORY_CLERK_CREATE.name())
                .antMatchers(PUT,"/api/v1/products/**").hasAnyAuthority(ADMIN_READ.name(), INVENTORY_CLERK_UPDATE.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) ->
                        SecurityContextHolder.clearContext());

        return httpSecurity.build();

    }
}
