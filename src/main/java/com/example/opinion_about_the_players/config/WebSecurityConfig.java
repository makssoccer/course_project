package com.example.opinion_about_the_players.config;

import com.example.opinion_about_the_players.models.Role;
import com.example.opinion_about_the_players.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable().csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/registration", "/teams", "/teams/{id}","/players", "/playersNoConf", "/players/{id}",
                             "/main", "/coaches", "/coaches/{id}","/review","/commentary").permitAll()
                .antMatchers("/players/add", "/coaches/add").hasAuthority(Role.USER.getAuthority())
                .antMatchers("/**").hasAuthority(Role.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}