package com.opc.paymybuddy.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();


        // Entry points with no authentification
        http.authorizeRequests()//
                .antMatchers(HttpMethod.GET, "/*").permitAll()
                .antMatchers(HttpMethod.POST, "/Add*").permitAll()
                .antMatchers("/actuator*").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/h2-console/**/**").permitAll();
                // Disallow everything else..
                //.anyRequest().authenticated();

    }


}
