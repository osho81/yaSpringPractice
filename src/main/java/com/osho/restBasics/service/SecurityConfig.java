package com.osho.restBasics.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Override
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

    }*/


    // If use this, eventually change roles to hasAuthority("read") in antMatchers
    // (This userDetailsService requires password encoder, even if NoOpPasswordEncoder)
    @Bean
    public UserDetailsService UserDetailsService() {
        var uds = new InMemoryUserDetailsManager(); // var allows for e.g. string input

        var user1 = User
                .withUsername("Osho")
                .password("1234")
                .authorities("read")
                .build();
        uds.createUser(user1);

        return uds;
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
//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().anyRequest().authenticated();

        http
                .httpBasic()
                .and()

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


        ////--------- Configure eventual specified cors policy -------/////

//        http.cors(c ->{
//            CorsConfigurationSource cs = request -> {
//                CorsConfiguration cc = new CorsConfiguration();
                  //Examples:
//                cc.setAllowedOrigins(List.of("http://127.0.0.1:8080", @http://localhost:9090@));
//                cc.setAllowedMethods(List.of("GET", "POST"));
//                return cc;
//            };
//            c.configurationSource(cs);
//        });



    }
}
