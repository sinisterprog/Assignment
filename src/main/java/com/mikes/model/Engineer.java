package com.mikes.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "engineer")
public class Engineer {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "engineer_id", unique = true, nullable = false)
	private Integer engineer_id;
	@Column
	@NotNull
	@Size(min = 1, max = 255)
	private String first_name;
	@Column
	@NotNull
	@Size(min = 1, max = 255)
	private String last_name;
	@Column
	@NotEmpty
	@Email
	private String email_addr;
	@Column
	@NotEmpty
	private String mobile_number;
	@Column
	private String skype_id;

	@Column
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dob = new Date();
	@Column
	private String website;
	@Column
	private String remarks;
	@Column
	@NotEmpty
	private String technical_skills;
	@Column
	private String photo_id;
	@Column
	private String address;

	@Column
	private char gender;

	@OneToOne
	@JoinColumn(name = "experience_id")
	@NotNull
	private Experience experience = new Experience();

	@OneToOne
	@JoinColumn(name = "qualification_id")
	@NotNull
	private Qualification qualification = new Qualification();

	public Integer getEngineer_id() {
		return engineer_id;
	}

	public void setEngineer_id(Integer engineer_id) {
		this.engineer_id = engineer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_addr() {
		return email_addr;
	}

	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getSkype_id() {
		return skype_id;
	}

	public void setSkype_id(String skype_id) {
		this.skype_id = skype_id;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDob() {
		return dob;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTechnical_skills() {
		return technical_skills;
	}

	public void setTechnical_skills(String technical_skills) {
		this.technical_skills = technical_skills;
	}

	public String getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualifications) {
		this.qualification = qualifications;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

}
