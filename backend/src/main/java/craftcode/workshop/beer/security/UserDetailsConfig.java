package craftcode.workshop.beer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {
    @Bean
    public UserDetailsService customUserDetailsService() {
        PasswordEncoder encoder = passwordEncoder();
        String encodedUserPassword = encoder.encode("craftcode123");
        String encodedAdminPassword = encoder.encode("admin");

        UserDetails user = User.builder()
                .username("user")
                .password(encodedUserPassword)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encodedAdminPassword)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
