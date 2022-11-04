package com.lm.app.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lm.app.model.CourseEntity;

@Repository
public interface CourseRepository extends MongoRepository<CourseEntity, Integer>{

	List<CourseEntity> findAllByTechnology(String technology);

	List<CourseEntity> findAllByTechnologyAndStartTimeAndEndTime(String technology, Date start, Date end);
	
////	@Query("select c from CourseEntity c where "
////			+ "c.technology=:technology"
////			+ " and CAST(c.startTime as date)=CAST(:startTime as date)"
////			+ " and CAST(c.endTime as date)=CAST(:endTime as date)")
//	@Query("{$and :[{'technology': ?0},{'start_time': ?1},{'end_time': ?2}] }")
//	List<CourseEntity> findAllByTechnologyAndDuration(String technology, Date start, Date end);

}
