package com.mikes.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mikes.dao.exception.DatabaseException;
import com.mikes.dao.persistence.PersistenceHelper;
import com.mikes.filehandling.FileHandler;
import com.mikes.model.Engineer;
import com.mikes.model.Experience;
import com.mikes.model.Qualification;
import com.mikes.viewObjects.EngineerViewObject;

@Controller
//@RequestMapping("/")
public class EngineerController {

	final static Logger logger = Logger.getLogger(EngineerController.class);

	@Autowired
	private PersistenceHelper persistenceHelper;

	/*
	 * This method will serve as default GET handler.
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/list")
	public ModelAndView handleRequest() throws Exception {
		List<Engineer> listEngineers = null;
		try {
			 listEngineers = persistenceHelper.getEngineers();
		} catch (DatabaseException ex) {
			return new ModelAndView("dbError");
		}
		ModelAndView model = new ModelAndView("EngineerList");
		model.addObject("listEngineers", listEngineers);
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("/EngineerForm");
		model.addObject("engineer", new EngineerViewObject());

		return model;
	}

	private void persistAndUpdate(EngineerViewObject evo, MultipartFile file)
			throws DatabaseException {
		String fileName = "";
		if (file != null) {
			fileName = file.getName();
		}
		Engineer e = persistenceHelper.toPersistableType(evo, fileName);
		persistenceHelper.saveOrUpdate(e);
		if (file != null && !file.isEmpty()) {
			FileHandler.saveFile(file, e.getEngineer_id());
		}

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(
			@RequestParam(value = "id", required = true) String id) {
		int asInt = Integer.parseInt(id);
		Engineer e = null;
		try {
			e = persistenceHelper.get(asInt);
		} catch (DatabaseException e1) {
			return new ModelAndView("/dbError");
		}
		ModelAndView model = new ModelAndView("/EngineerForm");
		model.addObject("engineer", persistenceHelper.fromPersistableType(e));
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("engineer") EngineerViewObject engineer,
			BindingResult result,
			@RequestParam(value = "image", required = false) MultipartFile file) {

		if (result.hasErrors()) {
			return "/EngineerForm";
		}
		try {
			persistAndUpdate(engineer, file);
		} catch (DatabaseException e) {
			return "/dbError";
		}

		return "redirect:/list";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String save(
			@Valid @ModelAttribute("engineer") EngineerViewObject engineer,
			BindingResult result,
			@RequestParam(value = "image", required = false) MultipartFile file) {

		if (result.hasErrors()) {
			return "/EngineerForm";
		}
		try {
			persistAndUpdate(engineer, file);
		} catch (DatabaseException e) {
			return "/dbError";
		}

		return "redirect:/list";
	}

	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String saveRegistration(@Valid Engineer engineer,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "EngineerForm";
		}

		try {
			persistenceHelper.saveOrUpdate(engineer);
		} catch (DatabaseException e) {
			return "/dbError";

		}
		return "redirect:/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id", required = true) String id) {
		int asInt = Integer.parseInt(id);
		Engineer e;
		try {
			e = persistenceHelper.get(asInt);
			persistenceHelper.delete(asInt);
		} catch (DatabaseException e1) {
			return "/dbError";

		}
		FileHandler.deleteFile(e.getPhoto_id());

		return "redirect:/list";
	}

	@ModelAttribute("/dbError")
	public String dbError() {
		return "dbError";
	}

	@ModelAttribute("qualificationListItems")
	public List<Qualification> getQualificationListItems() {
		try {
			return persistenceHelper.getQualifications();
		} catch (DatabaseException e) {
			return new ArrayList<Qualification>();
		}
	}

	@ModelAttribute("experienceListItems")
	public List<Experience> getExperienceListItems() {
		try {
			return persistenceHelper.getExperiences();
		} catch (DatabaseException e) {
			return new ArrayList<Experience>();
		}
	}

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(
				dateFormat, true));
	}

}