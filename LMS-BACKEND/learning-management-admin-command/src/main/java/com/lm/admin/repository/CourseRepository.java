package com.lm.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lm.admin.model.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer>{

}
