package com.mikes.dao.impl;

import java.util.List;

import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mikes.dao.interfaces.QualificationsDao;
import com.mikes.model.Qualification;

public class QualificationsDaoImpl implements QualificationsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public QualificationsDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Qualification> getQualifications() throws JDBCException {
		List<Qualification> listQualifications = (List<Qualification>) sessionFactory
				.getCurrentSession().createCriteria(Qualification.class).list();
		return listQualifications;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional

	public Qualification getQualificationByDescription(String description) throws JDBCException {
		String hql = "FROM Qualification WHERE description = :desc";
		
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("desc", description);
		List<Qualification> results = q.list(); 
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}
}
