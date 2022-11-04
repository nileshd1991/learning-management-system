package com.lm.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lm.models.Course;
import com.lm.models.EnrollDetails;
import com.lm.models.Token;
import com.lm.models.User;

@Service
public interface UserService {

	ResponseEntity<Token> validateUser(User user);

	ResponseEntity<Void> registerUser(User user);

	ResponseEntity<Void> enrollCourse(@Valid EnrollDetails enrollDetails);

	ResponseEntity<List<Course>> getUserEnrolledCourses(Integer userId);

	ResponseEntity<Void> removeCourseEnrollMent(Integer userId, Integer courseId);

}
