package com.mikes.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.hibernate.cfg.Environment;

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
public class HibernateContextConfiguration {
//	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {



		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
		// Hibernate settings equivalent to hibernate.cfg.xml's properties
		Properties settings = new Properties();
		settings.put(Environment.DRIVER, "org.h2.Driver");
		settings.put(Environment.URL, "jdbc:h2:mem:test");
		settings.put(Environment.USER, "sa");

		configuration.setProperties(settings);
		configuration.addAnnotatedClass(Engineer.class);
		configuration.addAnnotatedClass(Experience.class);
		configuration.addAnnotatedClass(Qualification.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
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
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:mem:test");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
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
