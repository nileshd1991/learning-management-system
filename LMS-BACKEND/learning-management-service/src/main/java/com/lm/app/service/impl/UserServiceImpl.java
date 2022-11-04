package com.lm.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lm.app.config.JwtUtil;
import com.lm.app.model.CourseEntity;
import com.lm.app.model.EnrollDetailsEntity;
import com.lm.app.repository.CourseRepository;
import com.lm.app.repository.EnrollDetailsRepository;
import com.lm.app.repository.UserRepository;
import com.lm.app.service.UserService;
import com.lm.app.service.exceptions.LmApiException;
import com.lm.models.Course;
import com.lm.models.EnrollDetails;
import com.lm.models.Token;
import com.lm.models.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollDetailsRepository enrollDetailsRepository;
	
	@Override
	public ResponseEntity<Token> validateUser(User user){
		com.lm.app.model.User user1 = userRepository.findByUserName(user.getUserName());
		if(user1.getRole().equals("ROLE_USER")) {
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
						);
			} catch (Exception e) {
				throw new LmApiException("Invalid username or password", HttpStatus.UNAUTHORIZED);
			}
			
			
			String tokenStr = jwtUtil.generateToken(user.getUserName());
			Token token=new Token();
			token.setToken(tokenStr);
			token.setUserId(user1.getUserId());
			return new ResponseEntity<Token>(token,HttpStatus.OK);
		}
		throw new LmApiException("Invalid username or password", HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<Void> registerUser(User user) {
		com.lm.app.model.User userEntity=modelMapper.map(user, com.lm.app.model.User.class);
		userEntity.setRole("ROLE_USER");
		userRepository.save(userEntity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> enrollCourse(@Valid EnrollDetails enrollDetails) {
		Optional<CourseEntity> courseOpt = courseRepository.findById(enrollDetails.getCourseId());
		if(courseOpt.isPresent()) {
			EnrollDetailsEntity entity=new EnrollDetailsEntity();
			entity.setUserId(enrollDetails.getUserId());
			entity.setCourseEntity(courseOpt.get());
			enrollDetailsRepository.save(entity);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			throw new LmApiException("Invalid course Id", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<Course>> getUserEnrolledCourses(Integer userId) {
		List<EnrollDetailsEntity> list= enrollDetailsRepository.findByUserId(userId);
		List<Course> courses=new ArrayList<>();
		for (EnrollDetailsEntity enrollment : list) {
			Course course=modelMapper.map(enrollment.getCourseEntity(), Course.class);
			courses.add(course);
		}
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> removeCourseEnrollMent(Integer userId, Integer courseId) {
		List<EnrollDetailsEntity> list= enrollDetailsRepository.findByUserId(userId);
		for (EnrollDetailsEntity entity : list) {
			if(entity.getCourseEntity().getId().equals(courseId)) {
				enrollDetailsRepository.delete(entity);
				break;
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
