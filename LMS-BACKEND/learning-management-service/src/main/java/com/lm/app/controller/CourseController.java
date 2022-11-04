package com.lm.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.lm.api.CourseApi;
import com.lm.app.service.CourseService;
import com.lm.models.ApiError;
import com.lm.models.Course;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@ApiResponses(value = {
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
				@Content(mediaType = "application/json", schema = @Schema(anyOf = ApiError.class)) }),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = {
				@Content(mediaType = "application/json", schema = @Schema(anyOf = ApiError.class)) }) })
@RequestMapping("/course")
@CrossOrigin
public class CourseController implements CourseApi{

	@Autowired
	private CourseService courseService;
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Course.class)) }) })
	@Override
	public ResponseEntity<Course> addCourse(@Valid Course course) {
		return courseService.addCourse(course);
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Course.class)) }) })
	@Override
	public ResponseEntity<Course> getCoursesById(Integer courseId) {
		return courseService.getCoursesById(courseId);
	}
	
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
//			@Content(mediaType = "application/json", schema = @Schema(anyOf = Course.class)) }) })
//	@Override
//	public ResponseEntity<Course> updateCourse(Integer courseId, @Valid Course course) {
//		return courseService.updateCourse(courseId,course);
//	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(anyOf = Void.class)) }) })
	@Override
	public ResponseEntity<Void> deleteCourse(Integer courseId) {
		return courseService.deleteCourse(courseId);
	}
	
	@Override
	public Optional<NativeWebRequest> getRequest() {
		return CourseApi.super.getRequest();
	}
}
