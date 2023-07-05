//package com.g24.authentication.model.service.impl;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.g24.authentication.model.entity.Token;
//import com.g24.authentication.model.entity.User;
//import com.g24.authentication.model.repository.TokenRepository;
//import com.g24.authentication.model.service.TokenService;
//
//@Service
//public class TokenServiceImpl implements TokenService {
//
//	@Autowired private TokenRepository tokenRepository;
//	
//	@Override
//	public Token createToken (User user, String type) {
//		Token token = new Token();
//		token.setToken(UUID.randomUUID().toString());
//		token.setUser(user);
//		token.setType(type);
//		token.setExpiryDate(30);
//        token = tokenRepository.save(token);
//        return token;
//	}
//}
