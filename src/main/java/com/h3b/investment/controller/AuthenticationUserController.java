package com.h3b.investment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.AuthenticationUser;
import com.h3b.investment.repository.AuthenticationUserRepository;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})

public class AuthenticationUserController {
	@Autowired
	AuthenticationUserRepository authenticationUserRepository;
	
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<AuthenticationUser> getUserById(@PathVariable("id") long id) throws ResourceNotFoundException{
		AuthenticationUser user = authenticationUserRepository.findById(id)
										.orElseThrow(()->
														new ResourceNotFoundException("User not found!"));
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/secured/user")
	@PreAuthorize("hasRole('ADMIN')")
	public List<AuthenticationUser> listUsers(){
		return authenticationUserRepository.findAll();
	}
	
	
}
