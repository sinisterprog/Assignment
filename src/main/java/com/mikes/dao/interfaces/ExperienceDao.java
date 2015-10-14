package com.mikes.dao.interfaces;

import java.util.List;

import org.hibernate.JDBCException;

import com.mikes.model.Experience;

public interface ExperienceDao {
	public List<Experience>getExperiences() throws JDBCException;

	public Experience getExperienceByDescription(String text) throws JDBCException;
}
