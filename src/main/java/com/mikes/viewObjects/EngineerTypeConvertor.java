package com.mikes.viewObjects;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mikes.dao.interfaces.ExperienceDao;
import com.mikes.dao.interfaces.QualificationsDao;
import com.mikes.model.Engineer;
import com.mikes.model.Experience;
import com.mikes.model.Qualification;

public class EngineerTypeConvertor {
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"dd/MM/yyyy");
	private static final Logger logger = Logger
			.getLogger(EngineerTypeConvertor.class);
	@Autowired
	private ExperienceDao experienceDao;
	@Autowired
	private QualificationsDao qualificationsDao;

	public final Engineer toPersistableType(EngineerViewObject ebv,
			String fileName) {
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

		Qualification q = qualificationsDao.getQualificationByDescription(ebv
				.getQualification());
		en.setQualification(q);

		Experience e = experienceDao.getExperienceByDescription(ebv
				.getExperience());
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
			;
		}
		return evo;
	}
}
