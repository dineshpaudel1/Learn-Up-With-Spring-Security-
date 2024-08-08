package com.example.LearnUp.System.config;

import com.example.LearnUp.System.jwt.JwtAuthenticationEntryPoint;
import com.example.LearnUp.System.jwt.JwtRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable()
            .and()
                .csrf(csrf-> csrf.disable())
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
            .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                            .requestMatchers(
                                    new AntPathRequestMatcher("/show/**"),
                                    new AntPathRequestMatcher("/auth/**"),
                                    new AntPathRequestMatcher("/h2-console/**"),
                                    new AntPathRequestMatcher("/api/users/**"),
                                    new AntPathRequestMatcher("/v3/api-docs/**"),
                                    new AntPathRequestMatcher("/swagger-ui/**"),
                                    new AntPathRequestMatcher("/swagger-ui.html"),
                                    new AntPathRequestMatcher("/api/photo"),
                                    new AntPathRequestMatcher("/api/contact/news-letter"),
                                    new AntPathRequestMatcher("/ws-api/**"),
                                    new AntPathRequestMatcher("/show/**"),
                                    new AntPathRequestMatcher("/api/courses/**"),
                                    new AntPathRequestMatcher("/api/my/**")
                            ).permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/api/admin/**"),
                                    new AntPathRequestMatcher("/api/roles/**"),
                                    new AntPathRequestMatcher("/bye")
                            ).hasRole("ADMIN")
                            .requestMatchers("/api/user/**").hasAnyRole("USER","ADMIN","HOSPITAL_MANAGER")
                            .requestMatchers("/goodbye").hasRole("HOSPITAL_MANAGER")
                            .anyRequest().authenticated()
            )
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
