package com.mikes.dao.interfaces;

import java.util.List;

import org.hibernate.JDBCException;

import com.mikes.model.Engineer;

public interface EngineerDao {
	public List<Engineer> getEngineers() throws JDBCException;

	public Engineer get(int id) throws JDBCException;

	public Integer saveOrUpdate(Engineer engineer) throws JDBCException;

	public void delete(int id) throws JDBCException;
}
