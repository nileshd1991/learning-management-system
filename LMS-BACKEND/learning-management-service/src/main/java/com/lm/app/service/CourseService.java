package com.lm.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.lm.models.Course;

public interface CourseService {

	public ResponseEntity<Course> addCourse(@Valid Course course);

	public ResponseEntity<Course> updateCourse(Integer courseId, @Valid Course course);

	public ResponseEntity<Void> deleteCourse(Integer courseId);

	public ResponseEntity<List<Course>> getAllCourses();

	public ResponseEntity<List<Course>> getCoursesByTechnology(String technology);

	public ResponseEntity<List<Course>> getCoursesByTechnologyDuration(String technology, Long from, Long to);

	public ResponseEntity<Course> getCoursesById(Integer courseId);

}
