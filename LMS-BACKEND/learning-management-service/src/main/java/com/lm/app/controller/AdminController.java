package com.lm.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lm.api.AdminApi;
import com.lm.app.service.AdminUserService;
import com.lm.models.ApiError;
import com.lm.models.Token;
import com.lm.models.User;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@ApiResponses(value = {
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
				@Content(mediaType = "application/json", schema = @Schema(anyOf = ApiError.class)) }),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = {
				@Content(mediaType = "application/json", schema = @Schema(anyOf = ApiError.class)) }) })
@RequestMapping("admin")
@CrossOrigin
public class AdminController implements AdminApi {

	@Autowired
	private AdminUserService adminUserService;
	
	@Override
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Token.class)) }) })
	public ResponseEntity<Token> adminLogin(@Valid User user) {
		System.out.println(user);
		return adminUserService.validateAdminUser(user);
	}
}
