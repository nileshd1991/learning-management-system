package com.lm.app.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lm.models.Course;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "LEARNING-MANAGEMENT-ADMIN-COMMAND")
public interface AdminCommandFeignClient {

	@PostMapping("/course/add")
	ResponseEntity<Course> addCourse(@RequestBody Course course);
	
	@PutMapping("/course/update/{course_id}")
	ResponseEntity<Course> updateCourse(@PathVariable("course_id") Integer courseId, @RequestBody Course course);
	
	@DeleteMapping("/course/delete/{course_id}")
	ResponseEntity<Void> deleteCourse(@PathVariable("course_id") Integer courseId);
}
