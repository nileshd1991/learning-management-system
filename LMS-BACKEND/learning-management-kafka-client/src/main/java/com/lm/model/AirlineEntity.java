package com.lm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the airline database table.
 * 
 */
@Entity(name = "AirlineEntity")
@Table(name = "airline")
@NamedQuery(name = "AirlineEntity.findAll", query = "SELECT a FROM AirlineEntity a")
public class AirlineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "airline_id", unique = true, nullable = false)
	private Integer airlineId;

	private Boolean active;

	@Column(length = 250)
	private String address;

	@Column(length = 250)
	private String contact;

	@Column(length = 250)
	private String name;

	public AirlineEntity() {
	}

	public Integer getAirlineId() {
		return this.airlineId;
	}

	public void setAirlineId(Integer airlineId) {
		this.airlineId = airlineId;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AirlineEntity [airlineId=" + airlineId + ", active=" + active + ", address=" + address + ", contact="
				+ contact + ", name=" + name + "]";
	}

}