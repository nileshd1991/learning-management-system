package com.lm.admin.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lm.admin.model.CourseEntity;
import com.lm.admin.repository.CourseRepository;
import com.lm.admin.service.CourseService;
import com.lm.admin.service.exceptions.LmApiException;
import com.lm.models.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
    private KafkaTemplate<String, CourseEntity> kafkaTemplateCourse;
	
	@Value("${kafka.course.topic}")
	private String courseTopic;
	
	@Value("${kafka.coursedelete.topic}")
	private String courseDeleteTopic;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public ResponseEntity<Course> addCourse(Course course) {
			CourseEntity courseEntity = modelMapper.map(course, CourseEntity.class);
			courseEntity=courseRepository.save(courseEntity);
			kafkaTemplateCourse.send(courseTopic, courseEntity);
			return getaddCourseResponse(courseEntity,course);
	}

	private ResponseEntity<Course> getaddCourseResponse(CourseEntity entity, Course course) {
		course.setCourseId(entity.getCourseId());
		ResponseEntity<Course> responseEntity=new ResponseEntity<Course>(course, HttpStatus.OK);
		return responseEntity;
	}

	@Override
	public ResponseEntity<Void> deleteCourse(Integer courseId) {
		Optional<CourseEntity> opt = courseRepository.findById(courseId);
		if(opt.isPresent()) {
			CourseEntity entity=opt.get();
			courseRepository.deleteById(courseId);
			kafkaTemplateCourse.send(courseDeleteTopic,entity);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		throw new LmApiException("Invalid Course Id", HttpStatus.NOT_FOUND);
	}
	

	@Override
	public ResponseEntity<Course> updateCourse(Integer courseId, @Valid Course course) {
		Optional<CourseEntity> opt = courseRepository.findById(courseId);
		if(opt.isPresent()) {
			CourseEntity entity=opt.get();
			CourseEntity coursetEntity = modelMapper.map(course, CourseEntity.class);
			coursetEntity.setCourseId(entity.getCourseId());
			coursetEntity=courseRepository.save(coursetEntity);
			kafkaTemplateCourse.send(courseTopic, coursetEntity);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		}
		throw new LmApiException("Invalid Course Id", HttpStatus.NOT_FOUND);
	}
}
