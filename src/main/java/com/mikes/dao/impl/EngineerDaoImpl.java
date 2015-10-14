package com.mikes.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mikes.dao.interfaces.EngineerDao;
import com.mikes.model.Engineer;
@Repository
public class EngineerDaoImpl implements EngineerDao {
	@Autowired
	private SessionFactory sessionFactory;

	public EngineerDaoImpl() {
	}

	public EngineerDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Engineer> getEngineers() {
		
		List<Engineer> listUser = (List<Engineer>) sessionFactory.getCurrentSession()
				.createCriteria(Engineer.class).list();

		return listUser;
		
	}

	@Override
	@Transactional
	public Engineer get(int id) {
		return (Engineer) sessionFactory.getCurrentSession().get(
				Engineer.class, new Integer(id));
	}

	@Override
	@Transactional
	public Integer saveOrUpdate(Engineer engineer) {
		sessionFactory.getCurrentSession().saveOrUpdate(engineer);
		return engineer.getEngineer_id();
	}


	@Override
	@Transactional
	public void delete(int id) {
		Engineer e = (Engineer) sessionFactory.getCurrentSession().get(
				Engineer.class, new Integer(id));
		if (e != null) {
			sessionFactory.getCurrentSession().evict(e);
			sessionFactory.getCurrentSession().delete(e);
		}
		
	}


}
