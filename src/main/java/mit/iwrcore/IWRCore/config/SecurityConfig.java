package mit.iwrcore.IWRCore.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("---------------filterChain-----------------");

        http.authorizeRequests()
                .requestMatchers("/", "/assets/**", "/fonts/**", "/js/**", "/libs/**", "/line/**", "/scss/**", "/tasks/**").permitAll()
                .requestMatchers("/main").permitAll()
                .requestMatchers("/manager/**").permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin(formLogin->
                formLogin.loginPage("/login")
                        .defaultSuccessUrl("/checkrole")
                        .failureUrl("/login?error"));
        http.csrf(csrf->csrf.disable());
        http.logout(logout->
                logout.permitAll());
        return http.build();
    }

}
