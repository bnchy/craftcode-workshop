package craftcode.workshop.beer.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/beers/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/breweries/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/classifications/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/h2-console/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_OK);
                            Map<String, String> data = new HashMap<>();
                            data.put("message", "Login successful");
                            new ObjectMapper().writeValue(response.getOutputStream(), data);
                        })
                        .failureHandler((request, response, exception) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            Map<String, String> data = new HashMap<>();
                            data.put("message", "Login failed");
                            new ObjectMapper().writeValue(response.getOutputStream(), data);
                        })
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}