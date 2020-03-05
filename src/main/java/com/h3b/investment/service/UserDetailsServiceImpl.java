package com.h3b.investment.service;


import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.h3b.investment.model.AuthenticationRole;
import com.h3b.investment.model.AuthenticationUser;
import com.h3b.investment.repository.AuthenticationUserRepository;


@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AuthenticationUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthenticationUser user = userRepository.findOneByUsername(username);
		
		UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
	      
	      Set<AuthenticationRole> setRoles = user.getRoles();
	      String[] arrRoles = new String[setRoles.size()];
	      
	      Iterator<AuthenticationRole> itAuthenticationRole = setRoles.iterator();
	      int i=0;
	      while(itAuthenticationRole.hasNext()) {
	    	  AuthenticationRole authenticationRole = itAuthenticationRole.next();
	    	  arrRoles[i] = authenticationRole.getName();
	      }

	      builder.roles(arrRoles);
	      
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }

	    return builder.build();
		
		
	}
	
	
}

