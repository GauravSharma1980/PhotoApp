package com.photoApp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photoApp.dto.UserDto;
import com.photoApp.model.LoginRequestModel;
import com.photoApp.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	  
	  private UserService userService;
	  
	  
	  private Environment environement;
	  
	  
	  public AuthenticationFilter(UserService usersService, 
				Environment environment, 
				AuthenticationManager authenticationManager) {
			this.userService = usersService;
			this.environement = environment;
			super.setAuthenticationManager(authenticationManager);
		}
	 

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);

			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>());

			return this.getAuthenticationManager().authenticate(authRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((User) authResult.getPrincipal()).getUsername();
		UserDto userDto = userService.getUserDetailsByEmail(userName);

		String stringToken = Jwts.builder().setSubject(userDto.getUserId())
				.setExpiration(new Date(
						System.currentTimeMillis() + Long.parseLong(environement.getProperty("token.expiration"))))
				.signWith(SignatureAlgorithm.HS512, environement.getProperty("token.secret")).compact();

		response.addHeader("token", stringToken);
		response.addHeader("userId", userDto.getUserId());

	}

}
