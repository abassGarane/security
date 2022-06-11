package com.abass.security.config;
import static com.abass.security.config.Roles.*;
import static com.abass.security.config.AppUserPermissions.*;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/","/css/*","/js/*")
//                .permitAll()
//                .antMatchers("/students/**")
//                .hasRole(ADMIN.name())
//                .antMatchers("/administration/**")
//                .hasAnyAuthority(TEACHER_WRITE.getPermission(), TEACHER_READ.getPermission(), STUDENT_READ.getPermission())
//                .antMatchers("/administration/teachers")
//                .hasAuthority(TEACHER_READ.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails student = User.builder()
                .username("abass")
                .password(passwordEncoder.encode("garane"))
                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        UserDetails admin = User.builder()
                .username("philos")
                .password(passwordEncoder.encode("philos"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails principal = User.builder()
                .username("principal")
                .password(passwordEncoder.encode("principal"))
//                .roles(PRINCIPAL.)
                .authorities(PRINCIPAL.getGrantedAuthorities())
                .build();
        UserDetails teacher = User.builder()
                .username("teacher")
                .password(passwordEncoder.encode("teacher"))
                .roles(TEACHER.name())
                .authorities(TEACHER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(student,admin,principal,teacher);
    }
}
