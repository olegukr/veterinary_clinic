package org.factoriaf5.vcp.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.csrf().disable()
    //         .authorizeRequests()
    //             .antMatchers("/api/patients/**").authenticated() 
    //         .httpBasic(); 
    //     return http.build();
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //         .withUser("admin")
    //         .password(passwordEncoder().encode("admin123")) // Пароль шифруется
    //         .roles("ADMIN");
    // }
}
