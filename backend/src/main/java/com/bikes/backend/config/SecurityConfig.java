package com.bikes.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName(null);

		return http
				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).csrfTokenRequestHandler(requestHandler))
				.httpBasic()
				.and()
				.sessionManagement(config ->
						config.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/api/csrf/").permitAll() // Everyone may get an CSRF TOKEN - controller tested
				.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll() // Everyone may sign up - controller tested
				.requestMatchers("/api/users/admin").hasRole("ADMIN") // controller tested
				.anyRequest().authenticated()
				.and()
				.build();
	}
}
