package com.lm.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lm.models.Token;
import com.lm.models.User;

@Service
public interface AdminUserService {

	ResponseEntity<Token> validateAdminUser(User body);

}
