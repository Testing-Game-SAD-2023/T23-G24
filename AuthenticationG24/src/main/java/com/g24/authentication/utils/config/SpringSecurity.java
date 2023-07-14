package com.g24.authentication.utils.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SpringSecurity
{
	@Autowired private UserDetailsService userDetailsService;

	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher()
	{
		return new HttpSessionEventPublisher();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{
		http.csrf(
				csrf -> csrf.disable())
		.authorizeHttpRequests(
				authorize -> authorize
				//.requestMatchers("/login**","/register**","/confirm-registration**",
				//		"/forgot-password**",
				//		"/reset-password**").permitAll()
				//.requestMatchers("/index").permitAll()
				.requestMatchers("/home","/userid").authenticated()
				.requestMatchers("/home/admin").hasRole("ADMIN")
				.requestMatchers("/home/user").hasRole("USER")
				.requestMatchers("/api/**").permitAll()
				//.requestMatchers("/static/**").permitAll()
				.anyRequest().permitAll()
				)
		.sessionManagement(
				session -> session.maximumSessions(1)
				)
		.formLogin(
				form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.permitAll()
				.defaultSuccessUrl("/home")
				)
		.logout(
				logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.clearAuthentication(true)					
				)
		;
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}
	
}