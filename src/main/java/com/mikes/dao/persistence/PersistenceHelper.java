package com.mikes.dao.persistence;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mikes.dao.exception.DatabaseException;
import com.mikes.dao.interfaces.EngineerDao;
import com.mikes.dao.interfaces.ExperienceDao;
import com.mikes.dao.interfaces.QualificationsDao;
import com.mikes.model.Engineer;
import com.mikes.model.Experience;
import com.mikes.model.Qualification;
import com.mikes.viewObjects.EngineerViewObject;

public class PersistenceHelper {

	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	final static Logger logger = Logger.getLogger(PersistenceHelper.class);

	@Autowired
	private QualificationsDao qualificationsDao;

	@Autowired
	private ExperienceDao experienceDao;

	@Autowired
	private EngineerDao engineerDao;

	public List<Experience> getExperiences() throws DatabaseException {
		try {
			return experienceDao.getExperiences();
		} catch (Exception ex) {
			logger.error("Failed to retrieve experiences",
					ex.getCause());
			throw new DatabaseException();
		}
	}

	public Experience getExperienceByDescription(String text) throws DatabaseException {
		try {
			return experienceDao.getExperienceByDescription(text);

		} catch (Exception ex) {
			logger.error("Failed to retrieve experience with description " + text,
					ex.getCause());
			throw new DatabaseException();
		}
	}

	public List<Qualification> getQualifications() throws DatabaseException {
		try {
			return qualificationsDao.getQualifications();
		} catch (Exception ex) {
			logger.error("Failed to retrieve qualifications",
					ex.getCause());
			throw new DatabaseException();
		}

	}

	public Qualification getQualificationByDescription(String description)
			throws DatabaseException {
		try {
			return qualificationsDao.getQualificationByDescription(description);
		} catch (Exception ex) {
			logger.error("Failed to retrieve qualification for " + description,
					ex.getCause());
			throw new DatabaseException();

		}
	}

	public List<Engineer> getEngineers() throws DatabaseException {
		try {
			return engineerDao.getEngineers();
		} catch (Exception ex) {
			logger.error("Failed to retrieve engineers",
					ex.getCause());
			throw new DatabaseException();

		}
	}

	public Engineer get(int id) throws DatabaseException {
		try {
			return engineerDao.get(id);
		} catch (Exception ex) {
			logger.error("Failed to retrieve engineer id " + id,
					ex.getCause());
			throw new DatabaseException();
		}
	}

	public Integer saveOrUpdate(Engineer engineer) throws DatabaseException {

		try {
			return engineerDao.saveOrUpdate(engineer);
		} catch (Exception ex) {
			logger.error(
					"Failed to persist engineer id "
							+ engineer.getEngineer_id(),
					ex.getCause());
			throw new DatabaseException();
		}
	}

	public void delete(int id) throws DatabaseException {
		try {
			engineerDao.delete(id);
		} catch (Exception ex) {
			logger.error("Failed to delete engineer id " + id,
					ex.getCause());
			throw new DatabaseException();
		}
	}

	public final Engineer toPersistableType(EngineerViewObject ebv,
			String fileName) throws DatabaseException {
		Engineer en = new Engineer();
		en.setAddress(ebv.getAddress());

		try {
			en.setDob(format.parse(ebv.getDob()));
		} catch (ParseException e) {
			logger.error("Failed to parse date " + ebv.getDob(), e);
		}
		en.setEmail_addr(ebv.getEmail_addr());
		en.setEngineer_id(ebv.getEngineer_id());
		en.setFirst_name(ebv.getFirst_name());
		en.setLast_name(ebv.getLast_name());
		en.setGender(ebv.getGender().charAt(0));
		en.setMobile_number(ebv.getMobile_number());
		en.setPhoto_id(fileName);
		en.setRemarks(ebv.getRemarks());
		en.setSkype_id(ebv.getSkype_id());
		en.setTechnical_skills(ebv.getTechnical_skills());
		en.setWebsite(ebv.getWebsite());

		Qualification q = getQualificationByDescription(ebv.getQualification());
		en.setQualification(q);

		Experience e = getExperienceByDescription(ebv.getExperience());
		en.setExperience(e);
		return en;

	}

	public final EngineerViewObject fromPersistableType(Engineer e) {
		EngineerViewObject evo = new EngineerViewObject();
		evo.setAddress(e.getAddress());
		evo.setDob(format.format(e.getDob()));
		evo.setEmail_addr(e.getEmail_addr());
		evo.setFirst_name(e.getFirst_name());
		evo.setLast_name(e.getLast_name());
		evo.setTechnical_skills(e.getTechnical_skills());
		evo.setEngineer_id(e.getEngineer_id());
		evo.setExperience(e.getExperience().getDescription());
		evo.setQualification(e.getQualification().getDescription());
		evo.setGender("" + e.getGender());
		evo.setMobile_number(e.getMobile_number());
		if (e.getPhoto_id() != null && !e.getPhoto_id().isEmpty()) {
			try {
				evo.setPhoto_id(new File(e.getPhoto_id()).toURI().toURL());
			} catch (MalformedURLException e1) {

				logger.error("Failed to parse file " + e.getPhoto_id(), e1);

			}

		}
		return evo;
	}
}
