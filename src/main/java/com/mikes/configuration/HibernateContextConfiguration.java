package com.mikes.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mikes.dao.impl.EngineerDaoImpl;
import com.mikes.dao.impl.ExperienceDaoImpl;
import com.mikes.dao.impl.QualificationsDaoImpl;
import com.mikes.dao.interfaces.EngineerDao;
import com.mikes.dao.interfaces.ExperienceDao;
import com.mikes.dao.interfaces.QualificationsDao;
import com.mikes.dao.persistence.PersistenceHelper;
import com.mikes.model.Engineer;
import com.mikes.model.Experience;
import com.mikes.model.Qualification;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.mikes")
public class HibernateContextConfiguration {
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
				dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClasses(Engineer.class);
		sessionBuilder.addAnnotatedClasses(Experience.class);
		sessionBuilder.addAnnotatedClasses(Qualification.class);

		return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		return properties;
	}

	/*
	 * Datasource
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/intersoftkk");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");

		return dataSource;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "engineerDao")
	public EngineerDao getEngineerDao(SessionFactory sessionFactory) {
		return new EngineerDaoImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "qualificationsDao")
	public QualificationsDao getQualificationsDao(SessionFactory sessionFactory) {
		return new QualificationsDaoImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "experienceDao")
	public ExperienceDao getExperienceDao(SessionFactory sessionFactory) {
		return new ExperienceDaoImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="persistenceHelper")
	public PersistenceHelper getPersistenceHelper() {
		return new PersistenceHelper();
	}
}
