package com.lm.app.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lm.app.model.CourseEntity;
import com.lm.app.repository.CourseRepository;
import com.lm.app.service.CourseService;
import com.lm.app.service.exceptions.LmApiException;
import com.lm.models.Course;

@Service
@EnableFeignClients
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private AdminCommandFeignClient adminCommandFeignClient;

	@Override
	public ResponseEntity<Course> addCourse(@Valid Course course) {
		return adminCommandFeignClient.addCourse(course);
	}

	@Override
	public ResponseEntity<Course> updateCourse(Integer courseId, @Valid Course course) {
		return adminCommandFeignClient.updateCourse(courseId, course);
	}

	@Override
	public ResponseEntity<Void> deleteCourse(Integer courseId) {
		return adminCommandFeignClient.deleteCourse(courseId);
	}

	@Override
	public ResponseEntity<List<Course>> getAllCourses() {
		List<CourseEntity> courseList = courseRepository.findAll();
		return getResponseObject(courseList);
	}

	private ResponseEntity<List<Course>> getResponseObject(List<CourseEntity> courseList) {
		List<Course> list=new ArrayList<>();
		for (CourseEntity courseEntity : courseList) {
			Course course=modelMapper.map(courseEntity, Course.class);
			list.add(course);
		}
		return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Course>> getCoursesByTechnology(String technology) {
		List<CourseEntity> courseList = courseRepository.findAllByTechnology(technology);
		return getResponseObject(courseList);
	}

	@Override
	public ResponseEntity<List<Course>> getCoursesByTechnologyDuration(String technology, Long from, Long to) {
		Timestamp startTime = new Timestamp(from);
		Timestamp endTime = new Timestamp(to);
		Date start=new Date(from);
		Date end=new Date(to);
		checkDateValidation(startTime,endTime);
		List<CourseEntity> courseList =courseRepository.findAllByTechnologyAndStartTimeAndEndTime(technology,start,end);
//		List<CourseEntity> courseList = courseRepository.findAllByTechnologyAndDuration(technology,start,end);
		return getResponseObject(courseList);
	}
	
	private void checkDateValidation(Timestamp startDate, Timestamp endDate) {
		Instant startDateInstant=startDate.toInstant();
		Instant endDateInstant=endDate.toInstant();
		if(endDateInstant.isBefore(startDateInstant)) {
			throw new LmApiException("Start date should be before return date", HttpStatus.BAD_REQUEST);
		}		
	}

	@Override
	public ResponseEntity<Course> getCoursesById(Integer courseId) {
		Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
		if(courseOptional.isPresent()) {
			Course course=modelMapper.map(courseOptional.get(), Course.class);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		}
		else {
			throw new LmApiException("Invalid courseId", HttpStatus.NOT_FOUND);
		}
	}
}
