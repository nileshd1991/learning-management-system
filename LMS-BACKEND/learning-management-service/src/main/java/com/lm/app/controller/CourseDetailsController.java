package com.lm.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.lm.api.CoursesApi;
import com.lm.app.service.CourseService;
import com.lm.models.ApiError;
import com.lm.models.Course;

import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/courses")
@CrossOrigin
public class CourseDetailsController implements CoursesApi{

	@Autowired
	private CourseService courseService;
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = Course.class))) }) })
	@Override
	public ResponseEntity<List<Course>> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = Course.class))) }) })
	@Override
	public ResponseEntity<List<Course>> getCoursesByTechnology(String technology) {
		return courseService.getCoursesByTechnology(technology);
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = Course.class))) }) })
	@Override
	public ResponseEntity<List<Course>> getCoursesByTechnologyDuration(String technology, Long from, Long to) {
		return courseService.getCoursesByTechnologyDuration(technology,from,to);
	}
	
	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return CoursesApi.super.getRequest();
	}
}
