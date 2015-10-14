package com.mikes.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mikes.dao.interfaces.ExperienceDao;
import com.mikes.model.Experience;

public class ExperienceDaoImpl implements ExperienceDao {
	@Autowired
	private SessionFactory sessionFactory;

	public ExperienceDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Experience> getExperiences() {
		List<Experience> listExperiences = (List<Experience>) sessionFactory
				.getCurrentSession().createCriteria(Experience.class).list();
		return listExperiences;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Experience getExperienceByDescription(String text) {
		String hql = "FROM Experience WHERE description = :desc";
		
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("desc", text);
		List<Experience> results = q.list(); 
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}

}
