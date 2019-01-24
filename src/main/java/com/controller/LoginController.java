package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Patient;
import com.repository.PatientRepository;

@Controller
public class LoginController {

	@Autowired
	private PatientRepository patientRepository;
	
	
	@RequestMapping(value="", method=RequestMethod.GET )
	public String authentication() {
		return "login.html";
	}
	
	
	
	@RequestMapping(value="/authentication",method=RequestMethod.POST)
	public String authentication(@RequestParam String username ,@RequestParam String password) 
	{
		Patient patient = patientRepository.findByUsername(username);
		if(patient.getPassword().equals(password)) 
		{
			return "redirect:/Patient/index";
		}
		else return "login";	
	}
	
}
