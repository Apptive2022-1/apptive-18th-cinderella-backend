package com.example.cinderella.config.auth;

import com.example.cinderella.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/main").hasRole(Role.USER.name())
                .antMatchers("/chatroom/1").permitAll() //로그인 하지 않은 사용자도 접근 가능
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/chatroom/1", true)
                .failureUrl("/fail")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
