package com.lm.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the airline database table.
 * 
 */
@Entity(name = "CourseEntity")
@Table(name = "course")
@NamedQuery(name = "CourseEntity.findAll", query = "SELECT a FROM CourseEntity a")
public class CourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", unique = true, nullable = false)
	private Integer courseId;

	@Column(name = "course_name",length = 250)
	private String courseName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name="description",length = 250)
	private String description;

	@Column(name="technology",length = 250)
	private String technology;
	
	@Column(name="launch_url",length = 250)
	private String launchUrl;

	public CourseEntity() {
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getLaunchUrl() {
		return launchUrl;
	}

	public void setLaunchUrl(String launchUrl) {
		this.launchUrl = launchUrl;
	}

	@Override
	public String toString() {
		return "CourseEntity [courseId=" + courseId + ", courseName=" + courseName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", description=" + description + ", technology=" + technology
				+ ", launchUrl=" + launchUrl + "]";
	}
}