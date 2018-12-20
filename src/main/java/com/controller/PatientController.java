package com.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Patient;
import com.repository.PatientRepository;

@Controller
public class PatientController 
{
	@Autowired
	PatientRepository patientRepository;
	
	
	@RequestMapping(value= {"/Patient","/Patient/index"})
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="size",defaultValue="5")int s,
			@RequestParam(name="motCle",defaultValue="")String mc) 
	{
		Page<Patient> pagePatient= patientRepository.chercher("%"+mc+"%",PageRequest.of(p,s));
		model.addAttribute("listPatient", pagePatient.getContent());
		int[] pages=new int[pagePatient.getTotalPages()];
		model.addAttribute("pages", pages);
		
		model.addAttribute("pageTitle", "Liste des patients");
		return "Patient/Index";
	}
	
	@RequestMapping(value="/Patient/{id}")
	public String details(Model model, @PathVariable(value = "id") Long id) 
	{
		Patient patient = patientRepository.getOne(id);
		
		model.addAttribute("patient", patient);
		model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		model.addAttribute("patientID",id);
		model.addAttribute("pageTitle", "Fiche du patient");
		return "Patient/Details";
	}
	
	@RequestMapping(value="/Patient/Delete")
	public String delete(Long id) 
	{
		patientRepository.deleteById(id);
		return "redirect:/Patient";
		
	}
	
	@RequestMapping(value="/Patient/create", method=RequestMethod.GET)
	public String create(Model model)
	{
		model.addAttribute("pageTitle", "Nouveau patient");
		model.addAttribute("patient", new Patient());
		return "Patient/Create";
	}
	
	@RequestMapping(value="/Patient/create", method=RequestMethod.POST)
	public String save(Model model,@Valid Patient patient, BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) {
			return "Patient/Create";
		}
		
		patientRepository.save(patient);
		model.addAttribute("patient", patient);
		
		return "redirect:/Patient/"+patient.getId();
	}
	
	@RequestMapping(value="/Patient/edit",method=RequestMethod.GET)
	public String edit(Model model, Long id) {
		Patient patient= patientRepository.getOne(id);
		model.addAttribute("patient", patient);
		model.addAttribute("pageTitle", "Modifier patient");
		return "Patient/Edit";
	}

}
