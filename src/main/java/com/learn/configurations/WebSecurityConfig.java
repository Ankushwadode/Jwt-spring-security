package com.learn.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learn.filters.JwtRequestFilters;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Autowired
	private JwtRequestFilters requestFilters;
	
	@Bean
	public  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
		return httpSecurity.csrf(c -> c.disable())
				.authorizeHttpRequests(ahr -> ahr.requestMatchers("/authenticaiton","/register").permitAll()) 
				.authorizeHttpRequests(ahr -> ahr.requestMatchers("/api/**").authenticated()) 
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(requestFilters, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
								/*alter native of above lambda function*/
	
//    @Bean 									
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/authenticate", "/register").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/api/**")
//                .authenticated().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
