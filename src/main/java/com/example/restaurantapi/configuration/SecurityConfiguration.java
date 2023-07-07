package com.example.restaurantapi.configuration;

import com.example.restaurantapi.services.user.implementation.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    public static final String[] USER_ENDPOINTS_WHITELIST = {
            "/order/get/**",
            "/order/update/**",
            "/order/new/**",
            "/user/get/**"
    };
    public static final String[] CHEF_ENDPOINTS_WHITELIST = {
            "/waiter/user/password/**"
    };
    public static final String[] WAITER_ENDPOINTS_WHITELIST = {
            "/waiter/user/password/**",
            "/table/update/**"
    };
    public static final String[] CASHIER_ENDPOINTS_WHITELIST = {
            "/bill/get/**",
            "/bill/new/**",
            "/cashier/user/password"
    };

    public static final String[] ADMIN_ENDPOINTS_WHITELIST = {
            "/chef/**",
            "/chefs/**",
            "/cashier/**",
            "/cashiers/**",
            "/waiter/**",
            "/waiters/**",
            "/role/**",
            "/admin/**",
            "/bill/**",
            "/bills/**",
            "/table/**",
            "/menu/**",
            "/order/**",
            "/orders/**",
            "/file/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(USER_ENDPOINTS_WHITELIST).hasRole("USER")
                        .requestMatchers(ADMIN_ENDPOINTS_WHITELIST).hasRole("ADMIN")
                        .requestMatchers(CASHIER_ENDPOINTS_WHITELIST).hasRole("CASHIER")
                        .requestMatchers(WAITER_ENDPOINTS_WHITELIST).hasRole("WAITER")
                        .requestMatchers(CHEF_ENDPOINTS_WHITELIST).hasRole("CHEF")
                        .anyRequest().permitAll())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder.BCryptVersion version = BCryptPasswordEncoder.BCryptVersion.$2Y;
        return new BCryptPasswordEncoder(version, 12, createSecureRandom() );
    }

    private static SecureRandom createSecureRandom() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException nae) {
            return new SecureRandom();
        }
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService.userDetailsService();
    }


}