package com.lm.admin.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lm.models.Course;

@Service
public interface CourseService {
	ResponseEntity<Course> addCourse(@Valid Course course);

	ResponseEntity<Void> deleteCourse(Integer courseId);

	ResponseEntity<Course> updateCourse(Integer courseId, @Valid Course course);
}
