package com.lm.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lm.app.config.JwtUtil;
import com.lm.app.repository.UserRepository;
import com.lm.app.service.AdminUserService;
import com.lm.app.service.exceptions.LmApiException;
import com.lm.models.Token;
import com.lm.models.User;

@Service
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseEntity<Token> validateAdminUser(User body){
		com.lm.app.model.User user1 = userRepository.findByUserName(body.getUserName());
		if(user1.getRole().equals("ROLE_ADMIN")) {
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(body.getUserName(), body.getPassword())
						);
			} catch (Exception e) {
				throw new LmApiException("Invalid username or password", HttpStatus.UNAUTHORIZED);
			}
			
			String tokenStr = jwtUtil.generateToken(body.getUserName());
			Token token=new Token();
			token.setToken(tokenStr);
			token.setUserId(user1.getUserId());
			return new ResponseEntity<Token>(token,HttpStatus.OK);
		}
		throw new LmApiException("Invalid username or password", HttpStatus.UNAUTHORIZED);
	}

}
