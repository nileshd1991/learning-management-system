package com.lm.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lm.api.UserApi;
import com.lm.app.service.UserService;
import com.lm.models.ApiError;
import com.lm.models.Course;
import com.lm.models.EnrollDetails;
import com.lm.models.Token;
import com.lm.models.User;

import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("user")
@CrossOrigin
public class UserController implements UserApi {

	@Autowired
	private UserService userService;
	
	@Override
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Token.class)) }) })
	public ResponseEntity<Token> userLogin(@Valid User user) {
		System.out.println(user);
		return userService.validateUser(user);
	}
	
	@Override
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = User.class)) }) })
	public ResponseEntity<Void> registerUser(@Valid User user) {
		return userService.registerUser(user);
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Void.class)) }) })
	@Override
	public ResponseEntity<Void> enrollCourse(@Valid EnrollDetails enrollDetails) {
		return userService.enrollCourse(enrollDetails);
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = Course.class))) }) })
	@Override
	public ResponseEntity<List<Course>> getUserEnrolledCourses(Integer userId) {
		return userService.getUserEnrolledCourses(userId);
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Void.class)) }) })
	@Override
	public ResponseEntity<Void> removeCourseEnrollMent(Integer userId, Integer courseId) {
		return userService.removeCourseEnrollMent(userId, courseId);
	}
}
