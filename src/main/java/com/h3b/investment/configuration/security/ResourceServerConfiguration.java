package com.h3b.investment.configuration.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	 
	@Override
	public void configure(HttpSecurity http) throws Exception {
	 http.authorizeRequests()
	 						.antMatchers(	"/oauth/token", 
	 										"/oauth/authorize**",
	 										"/api/v1/user/*")
	 						.permitAll();  
	 		
	 }
	
}
