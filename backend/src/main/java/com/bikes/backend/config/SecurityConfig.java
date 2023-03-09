package com.bikes.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
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
				.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()))
				.and()
				.sessionManagement(config ->
						config.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.authorizeHttpRequests()
//				.requestMatchers(HttpMethod.GET, "/api/csrf/").permitAll() // Everyone may get an CSRF TOKEN - controller tested
//				.requestMatchers(HttpMethod.POST, "/api/users/").permitAll() // Everyone may sign up - controller tested
//				.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll() // Everyone may log in
				.requestMatchers(HttpMethod.POST, "/api/users/me").authenticated()
				.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/**").authenticated()
				.requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
				.requestMatchers(HttpMethod.GET, "/api/bikes/").authenticated()
				.requestMatchers("/api/users/admin").hasRole("ADMIN") // controller tested
				.anyRequest().permitAll()
				.and()
				.logout(logout -> logout
						.logoutUrl("/api/users/logout")
						.clearAuthentication(true)
						.invalidateHttpSession(true)
						.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
						.permitAll())
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
}
