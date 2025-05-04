/*
 * References
 *
 * (n.d.). Disable security for unit tests with spring boot. Stackoverflow. https://stackoverflow.com/questions/31169720/disable-security-for-unit-tests-with-spring-boot
 *
 * (2024, January 2). Spring boot profiles not working correctly. Stackoverflow. Retrieved May 3, 2025, from https://stackoverflow.com/questions/77681970/spring-boot-profiles-not-working-correctly
 *
 * (2018, March 23). Disable security for unit tests with spring boot. Stackoverflow. Retrieved May 3, 2025, from https://stackoverflow.com/questions/31169720/disable-security-for-unit-tests-with-spring-boot
 *
 * Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 *
 */
package com.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Profile("test")
@Configuration
public class TestSecurityConfig {

    // Encoder
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Sets up security configuration
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/monthly-books/list", "monthly-books/new", "monthly-books").hasRole("ADMIN")
                        .anyRequest().permitAll()
                ).csrf().disable()
                .httpBasic(withDefaults())
                .formLogin().loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();
        return http.build();
    }

    /**
     * User definition for application
     * @return InMemoryUserDetailsManager
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("USER")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
