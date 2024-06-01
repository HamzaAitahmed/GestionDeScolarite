package com.emsi.gestiondescolarite.config;



import com.emsi.gestiondescolarite.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }


//    Pour donner la permission au Static Directory
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {

//        http.authorizeRequests((authorize) -> authorize
//                .requestMatchers("/User/**").hasRole("USER")
//                .requestMatchers("/Admin/**").hasRole("ADMIN"));

        http
                .authorizeRequests(ar -> ar.requestMatchers("/home","/Auth/**","/Rest/**","/bootstrap/**","/css/**","/fonts/**","/images/**","/scss/**").permitAll().anyRequest().authenticated());
//                .requestMatchers("/home/**","/login/**","/register/**","/logout/**","/bootstrap/**","/css/**","/fonts/**","/images/**","/scss/**").permitAll()
//                .anyRequest().authenticated();

//        http.authorizeRequests((authorize) -> authorize
//                .requestMatchers("/Admin/**").hasRole("ADMIN")
////                .requestMatchers("/User/**").hasRole("USER")
//                .anyRequest().authenticated()
//        );

        http
                .formLogin(ar -> ar.loginPage("/home"));// methode post doesnt work ???
//                .formLogin()// methode post doesnt work ???
//                .loginPage("/home");

        http.csrf(cs -> cs.disable());

        return http.build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
//        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
