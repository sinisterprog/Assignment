package com.mikes.editorsupport;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.mikes.dao.interfaces.ExperienceDao;
import com.mikes.model.Experience;

public class ExperienceTypeEditorSupport extends PropertyEditorSupport {
	@Autowired
	private ExperienceDao experienceDao;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		Experience experience = experienceDao.getExperienceByDescription(text);
		setValue(experience);
	}

	@Override
	public String getAsText() {
		Experience e = (Experience) getValue();
		return e.getDescription();

	}
}
