package dev.midnightcoder.portfolio.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.stereotype.Service;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

/**
 * @author Glabay | Glabay-Studios
 * @project Portfolio
 * @social Discord: Glabay
 * @since 2026-06-08
 */
@Service
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/blogs",
                    "/blogs/**",
                    "/projects",
                    "/projects/**",
                    "/login",
                    "/register",
                    "/error",
                    "/api/auth/**"
                ).permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/profiles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/favicon.ico"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .addLogoutHandler(new HeaderWriterLogoutHandler(
                    new ClearSiteDataHeaderWriter(COOKIES)
                ))
            )
            .headers(AbstractHttpConfigurer::disable)
            .build();
    }
}
