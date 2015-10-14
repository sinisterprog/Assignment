package com.mikes.editorsupport;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import com.mikes.model.Experience;

public class EditorRegestrar implements PropertyEditorRegistrar{

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registery) {
		registery.registerCustomEditor(Experience.class, new ExperienceTypeEditorSupport());
		
	}

}
