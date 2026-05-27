package it.aulab.selfwork_spring_data.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String DIRECTIVES = "\"default-src 'self' ; img-src 'self'; script-src 'self' cdn.jsdelivr.net 'unsafe-inline'; style-src 'self' cdn.jsdelivr.net cdnjs.cloudflare.com ; font-src cdnjs.cloudflare.com\"";

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userManager(){
        UserBuilder user = User.withUsername("user").password(encoder().encode("12345678")); //creazione di un utente user in memoria
        UserBuilder admin = User.withUsername("admin").password(encoder().encode("admin12345678")); // creazione di un utente admin in memoria
        return new InMemoryUserDetailsManager(user.build(), admin.build());
    }

    @Bean
    public SecurityFilterChain configSecurityFilterChain(HttpSecurity http) throws Exception{
        // login di default
        // http.authorizeHttpRequests(
        //     (authorize)->authorize.requestMatchers("/api/**").permitAll().anyRequest().authenticated()).formLogin(Customizer.withDefaults()); 
        http.authorizeHttpRequests(
            (authorize)->authorize.requestMatchers("/api/**").permitAll().anyRequest().authenticated()).formLogin((formLogin)->formLogin.loginPage("/login")
            .defaultSuccessUrl("/authors", true)
            .permitAll())
            .logout((logout)->logout.logoutUrl("/logout").logoutSuccessUrl("/"))
            .csrf(
                (csrf)->csrf.ignoringRequestMatchers("/api/**")
            )
            .headers(
                (headers)->headers.xssProtection(Customizer.withDefaults()).contentSecurityPolicy(Customizer.withDefaults())
                .contentSecurityPolicy((csp)->csp.policyDirectives(DIRECTIVES))
            )
            ;

        return http.build();
    }
}
