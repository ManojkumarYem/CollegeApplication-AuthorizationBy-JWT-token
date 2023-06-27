package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.auth.service.CustomOauth2UserService;
import com.auth.service.JWTFilter;
import com.auth.service.UserDetailService;

@Component
@EnableWebSecurity
@CrossOrigin
public class Config extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomOauth2UserService customOauth2UserService;

	@Autowired
	private JWTFilter jwtFilter;

	@Autowired
	private UserDetailService Service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(Service);
	}

	@Bean
	public PasswordEncoder encoder() {

		return NoOpPasswordEncoder.getInstance();

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//
//		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().antMatchers("/page/**")
//				.permitAll().anyRequest().authenticated().and().oauth2Login().userInfoEndpoint()
//				.userService(customOauth2UserService);
//		;

		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().antMatchers("/logout")
				.permitAll().antMatchers("/").permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2Login().userInfoEndpoint()
				.userService(customOauth2UserService)
//				.userInfoEndpoint().userService(userService).and()
		;

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
