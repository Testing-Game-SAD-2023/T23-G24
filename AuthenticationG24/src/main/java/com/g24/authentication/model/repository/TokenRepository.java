package com.g24.authentication.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g24.authentication.model.entity.Token;
import com.g24.authentication.model.entity.User;

public interface TokenRepository extends JpaRepository<Token, Long> 
{
	Token findByToken(String token);

	Token findByUser(User user);
}
