package com.example.emsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.emsservice.config.ApplicationUserPermission.EMPLOYEE_READ;
import static com.example.emsservice.config.ApplicationUserPermission.EMPLOYEE_WRITE;
import static com.example.emsservice.config.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //PROTECTION FO R REQUEST PROCCESED BY BROWSER, AVOID HACKERS INJECTION, SERVER SEND TOKEN TO FRONTEND
                //FLOW:
                // FIRST LOGIN(FROM FRONTEND TO BACKEND)->
                //          SECOND SERVER SEND TOKEN AFTER AUTENTICATE ED->
                //                          THIRD FRONT END SEND REQUEST AND INCLUDE BACK THE TOKEN THAT RECEIVED FROM SERVER X-XSRF-TOKEN ->
                //                                  FORTH SERVER VALIDATES TOKEN
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "index","/css/*", "/js/*").permitAll()
//                .antMatchers("/ems/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST, "ems/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "ems/**").hasAnyRole(ADMIN.name(), EMPLOYEE1.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails jhonUser = User.builder()
                .username("jhon")
                .password(passwordEncoder.encode("a"))
                .roles(EMPLOYEE.name())
                .build();
        UserDetails peterUser = User.builder()
                .username("peter")
                .password(passwordEncoder.encode("b"))
                .roles(ADMIN.name())
                .build();
        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("c"))
                .roles(EMPLOYEE1.name())
                .build();
        return new InMemoryUserDetailsManager(jhonUser, peterUser, tomUser);
    }

}
