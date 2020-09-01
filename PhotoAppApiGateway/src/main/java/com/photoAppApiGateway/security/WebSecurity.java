package com.photoAppApiGateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	public void configure(HttpSecurity http) throws Exception {
		// this would not cache the JWT Token in the header thus would have to provide
		// it in the header every time since
		// would not cache any session information from any client.
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests().antMatchers("/users-ws/h2-console/**").permitAll()
				.antMatchers(HttpMethod.POST, "/users-ws/login").permitAll()
				.antMatchers(HttpMethod.POST, "/users-ws/users").permitAll().anyRequest().authenticated().and()
				.addFilter(new AuthorizationFilter(environment, authenticationManager()));

		http.addFilter(new AuthorizationFilter(environment, authenticationManager()));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
