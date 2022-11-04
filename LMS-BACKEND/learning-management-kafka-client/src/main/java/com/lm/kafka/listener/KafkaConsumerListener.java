package com.lm.kafka.listener;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lm.kafka.model.CourseEntity;
import com.lm.kafka.repository.CourseRepository;

@Service
public class KafkaConsumerListener {
	
	@Autowired
	private CourseRepository courseRepository;
	
    @KafkaListener(topics = "#{'${kafka.course.topic}'}", groupId="group_id", containerFactory = "courseKafkaListenerFactory")
    public void consumeJson(CourseEntity entity) {
    	saveCourseEntity(entity);
        System.out.println("Consumed Course Message: " + entity);
    }
    
    @KafkaListener(topics = "#{'${kafka.coursedelete.topic}'}", groupId="group_id1", containerFactory = "courseKafkaListenerFactory")
    public void consumeJsonForDelete(CourseEntity entity) {
    	deleteCourseEntity(entity);
        System.out.println("Consumed Course Message: " + entity);
    }
    
    @Transactional
    private void saveCourseEntity(CourseEntity entity) {
    	Optional<CourseEntity> entityOpt = courseRepository.findById(entity.getId());
    	CourseEntity courseEntityToPersist=null;
//    	System.out.println(entityOpt.get());
    	if(entityOpt.isPresent()) {
    		courseEntityToPersist=mapCourseEntityToExistingData(entity,entityOpt.get());
    	}
    	else {
    		courseEntityToPersist=mapCourseEntityToNewData(entity);
    	}
    	courseRepository.save(courseEntityToPersist);
	}
    
    @Transactional
    private void deleteCourseEntity(CourseEntity entity) {
    	Optional<CourseEntity> entityOpt = courseRepository.findById(entity.getId());
    	if(entityOpt.isPresent()) {
    		courseRepository.deleteById(entityOpt.get().getId());
    	}
    	else {
    		System.out.println("Hello");
    	}
	}

	private CourseEntity mapCourseEntityToNewData(CourseEntity entity) {
		CourseEntity courseEntityDB=new CourseEntity();
    	courseEntityDB.setCourseId(entity.getId());
    	courseEntityDB.setCourseName(entity.getCourseName());
    	courseEntityDB.setStartTime(entity.getStartTime());
    	courseEntityDB.setEndTime(entity.getEndTime());
    	courseEntityDB.setTechnology(entity.getTechnology());
    	courseEntityDB.setDescription(entity.getDescription());
    	courseEntityDB.setLaunchUrl(entity.getLaunchUrl());
		return courseEntityDB;
	}

	private CourseEntity mapCourseEntityToExistingData(CourseEntity entity, CourseEntity courseEntityDB) {
		courseEntityDB.setCourseName(entity.getCourseName());
    	courseEntityDB.setStartTime(entity.getStartTime());
    	courseEntityDB.setEndTime(entity.getEndTime());
    	courseEntityDB.setTechnology(entity.getTechnology());
    	courseEntityDB.setDescription(entity.getDescription());
    	courseEntityDB.setLaunchUrl(entity.getLaunchUrl());
		return courseEntityDB;
	}
}