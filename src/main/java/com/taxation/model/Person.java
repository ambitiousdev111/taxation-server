package com.taxation.model;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "person_id")
	private Integer personId;

	@Column(length = 50)
	@NotBlank
	@NotNull
	@Size(min = 2, max = 50, message = "Name should have at least two charaters")
	private String name;

	@Column(length = 10)
	@NotBlank(message = "Phone should not be blank")
	@NotNull(message = "Phone should not be null")
	@Size(min = 10, max = 10, message = "Phone number should be 10 digits")
	private String phone;

	@Column(name = "samagra_id", length = 8, unique = true)
	@NotBlank(message = "Samagra Id should not be blank")
	@NotNull(message = "Samagra Id should not be blank")
	@Size(min = 8, max = 8, message = "Samagra Id should be 8 digits")
	private String samagraId;

	@Column(length = 50)
	private String father;

	@Column(length = 6)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(length = 50)
	private String husband;

	@Column(length = 50)
	@NotBlank
	@NotNull
	private String caste;


//	@ManyToOne
//	@JoinColumn
////	@NotBlank
////	@NotNull
//	private User createdBy;

//	@CreationTimestamp
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created_at", nullable = false)
//	private Date createdAt;


	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSamagraId() {
		return samagraId;
	}

	public void setSamagraId(String samagraId) {
		this.samagraId = samagraId;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getHusband() {
		return husband;
	}

	public void setHusband(String husband) {
		this.husband = husband;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}
//
//	public User getCreatedBy() {	return createdBy;	}
//
//	public void setCreatedBy(User createdBy) {
//		this.createdBy = createdBy;
//	}

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
}
