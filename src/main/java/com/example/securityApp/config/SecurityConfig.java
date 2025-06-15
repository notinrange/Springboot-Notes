package com.example.securityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //defining own security conf and disabling the default one
public class SecurityConfig {


    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{

        // Disabling CSRF Token
        http.csrf(customizer -> customizer.disable());

        // Enable Authorization
        // http.authorizeHttpRequests(request->request.anyRequest().authenticated());

        // Not adding Auth for login and register
        http.authorizeHttpRequests(request->request
                        .requestMatchers("register","login")
                        .permitAll()
                        .anyRequest()
                        .authenticated());

        
        

        // Enable Login Form
        // http.formLogin(Customizer.withDefaults());

        // Enable HTTP response for POSTMAN
        http.httpBasic(Customizer.withDefaults());

        // Making Session Stateless by which we dont have to worry about session ID(It means har request hit hone par login form aayega)
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // Contorller -> Accepts requests and pass to service
        // Service -> do business logic
        // Repository -> connect with database

        // Returns object of security filter chain
        return http.build();


    }


    // @Bean
    // public UserDetailsService UserDetailService(){
    //     UserDetails user1 = User
    //                             .withDefaultPasswordEncoder()
    //                             .username("notinrange")
    //                             .password("notinrange")
    //                             .roles("USER")
    //                             .build();

    //     UserDetails user2 = User
    //                             .withDefaultPasswordEncoder()
    //                             .username("notinrange2")
    //                             .password("notinrange2")
    //                             .roles("USER")
    //                             .build();


    //     return new InMemoryUserDetailsManager(user1,user2);
    // }

    // Authentication provider for user object authentication
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // decrpting encrypted password while authenticating user
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    // Authentication Manager will talk to authentication provider
}
