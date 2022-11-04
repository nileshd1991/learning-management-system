package com.lm.app.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EnrollDetails")
public class EnrollDetailsEntity   {

	@Id
	private String id;
	
	private Integer userId;

	private CourseEntity courseEntity;

	public EnrollDetailsEntity(String id, Integer userId, CourseEntity courseEntity) {
		super();
		this.id = id;
		this.userId = userId;
		this.courseEntity = courseEntity;
	}

	public EnrollDetailsEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}

	public void setCourseEntity(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}
}

