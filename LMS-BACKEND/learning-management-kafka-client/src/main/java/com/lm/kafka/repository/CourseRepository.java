package com.lm.kafka.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lm.kafka.model.CourseEntity;

@Repository
public interface CourseRepository extends MongoRepository<CourseEntity, Integer>{
	

}
