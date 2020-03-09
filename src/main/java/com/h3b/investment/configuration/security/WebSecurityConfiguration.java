package com.h3b.investment.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailService")
	public UserDetailsService userDetailsService;
	
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.userDetailsService(userDetailsService);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

}