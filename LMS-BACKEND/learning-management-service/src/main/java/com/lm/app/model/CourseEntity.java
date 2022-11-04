package com.lm.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Course")
public class CourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name = "course_name", length = 250)
	private String courseName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "technology", length = 250)
	private String technology;

	@Column(name = "launch_url", length = 250)
	private String launchUrl;

	public CourseEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "CourseEntity [id=" + id + ", courseName=" + courseName + ", startTime=" + startTime + ", endTime="
				+ endTime + ", description=" + description + ", technology=" + technology + ", launchUrl=" + launchUrl
				+ "]";
	}
}