/*
package com.osho.restBasics.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


////---------------- KEYCLOAK CLASS -------------////
// uncomment this for keycloak - comment SecurityConfig
////----------- KEYCLOAK CONFIGURATION ----------////

@KeycloakConfiguration
public class SecurityConfigKeyCloak extends KeycloakWebSecurityConfigurerAdapter {
    // Keycloak authentication manager
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http

                // Test these again with the added /; test also with quarkus 19
                .authorizeRequests() // NOT authorizeHttpRequests() !!!
//                .antMatchers("/api/v1/person/all").hasRole("user")
//                .antMatchers("/api/v1/person/{id}").hasRole("admin")
//                .antMatchers(HttpMethod.POST,"/api/v1/person").hasRole("admin")

                .antMatchers("/api/v1/person/all/**").permitAll()
                .antMatchers("/api/v1/person/{id}").hasRole("user")
                .antMatchers(HttpMethod.POST).hasRole("admin")
                .antMatchers(HttpMethod.PUT).hasAnyRole("admin", "user")
//                .antMatchers(HttpMethod.DELETE).hasRole("admin")
                .antMatchers("/api/v1/person/delete/{id}").hasRole("admin")

                .anyRequest().authenticated();
        http.csrf().disable();
    }
}
*/