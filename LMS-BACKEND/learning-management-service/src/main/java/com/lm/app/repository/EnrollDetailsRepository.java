package com.lm.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lm.app.model.EnrollDetailsEntity;

@Repository
public interface EnrollDetailsRepository extends MongoRepository<EnrollDetailsEntity, String>{

	List<EnrollDetailsEntity> findByUserId(Integer userId);
	
}
