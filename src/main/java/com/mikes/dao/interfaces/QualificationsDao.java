package com.mikes.dao.interfaces;

import java.util.List;

import org.hibernate.JDBCException;

import com.mikes.model.Qualification;

public interface QualificationsDao {
	public List<Qualification> getQualifications() throws JDBCException;

	public Qualification getQualificationByDescription(String description)
			throws JDBCException;
}
