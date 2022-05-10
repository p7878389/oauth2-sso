package com.shareworks.auth.server.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author martin.peng
 */
@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SimpleUrlAuthenticationFailureHandler customAuthenticationFailureHandler;

    private final AccessDeniedHandler customAccessDeniedHandler;

    private final SavedRequestAwareAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final LogoutSuccessHandler customLogoutSuccessHandler;

    private final UserDetailsService customUserDetailsService;

    private final AuthenticationEntryPoint customUnauthorizedEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().requestMatchers()
                .antMatchers("/login", "/oauth/authorize", "/oauth/login")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .exceptionHandling()
//                .accessDeniedHandler(customAccessDeniedHandler)
//                .authenticationEntryPoint(customUnauthorizedEntryPoint)
                .and()
                .logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .deleteCookies("SSO-JSESSIONID")
        ;
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
