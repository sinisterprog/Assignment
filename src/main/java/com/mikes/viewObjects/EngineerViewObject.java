package com.mikes.viewObjects;

import java.net.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EngineerViewObject {

	private Integer engineer_id;

	@NotNull
	@Size(min = 1, max = 255)
	private String first_name;

	@NotNull
	@Size(min = 1, max = 255)
	private String last_name;

	@NotEmpty
	@Email
	private String email_addr;

	@NotEmpty
	private String mobile_number;

	private String skype_id;
	@NotEmpty
	private String dob;

	private String website;

	private String remarks;

	@NotEmpty
	private String technical_skills;

	private URL photo_id;

	private String address;

	@NotEmpty
	@Size(min=1, max=1)
	private String gender;
	@NotEmpty
	private String experience;

	@NotEmpty
	private String qualification;

	private String imageSource;

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

	public URL getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(URL url) {
		this.photo_id = url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	public void setImageSource(String source) {
		this.imageSource = source;
	}
	public String getImageSource() {
		return this.imageSource;
	}

}
