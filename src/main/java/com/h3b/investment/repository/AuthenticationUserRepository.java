package com.h3b.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.h3b.investment.model.AuthenticationUser;

public interface AuthenticationUserRepository extends JpaRepository<AuthenticationUser, Long>{

	AuthenticationUser findOneByUsername(String username);
	
}