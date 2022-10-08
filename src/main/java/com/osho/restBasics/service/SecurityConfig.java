package com.osho.restBasics.service;

import org.apache.catalina.User;
import org.hibernate.id.enhanced.NoopOptimizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("super")
                .password("1234")
                .roles("ADMIN", "USER")
                .and()
                .withUser("duper")
                .password("12345")
                .roles("ADMIN")
                .and()
                .withUser("user10")
                .password("1010")
                .roles("USER")
                .and()
                .withUser("user20")
                .password("2020")
                .roles("USER");

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() throws Exception {
        return NoOpPasswordEncoder.getInstance();
    }

    // Example implementing auth exception in web sec for e.g. H2 db
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/h2-console/**");
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()

                .authorizeRequests()

                .antMatchers("/api/v1/person/all/**").permitAll()
                .antMatchers("/api/v1/person/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
//                .antMatchers("/api/v1/person/logiccreate").hasRole("ADMIN") // Covered above

//                .anyRequest().authenticated() // Other request paths need at least login
                .anyRequest().hasRole("ADMIN") // Other request paths need admin authorization

                .and().formLogin()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/api/v1/person/all");

        http.csrf().disable();
    }


}
