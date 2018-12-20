package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.MesureMembreInf;
import com.entities.MesureMembreSup;
import com.entities.Mesures;
import com.entities.Patient;
import com.enumeration.TypeProthese;
import com.repository.MesureMembreInfRepository;
import com.repository.MesureMembreSupRepository;
import com.repository.MesuresRepository;
import com.repository.PatientRepository;

@Controller
public class MesureController 
{
	@Autowired MesuresRepository mesureRepository;
	@Autowired MesureMembreSupRepository mesureSupRepository;
	@Autowired MesureMembreInfRepository mesureInfRepository;
	@Autowired PatientRepository patientRepository;
	
	
	// URL-Pattern --> /Patient/1/Mesure/index
	@RequestMapping(value="/Patient/{id}/Mesure/index", method=RequestMethod.GET)
	public String index(@PathVariable(value="id") Long id, Model model)
	{
		Patient patient = patientRepository.findById(id).get();
		List<Mesures> listMesuresSUP = mesureSupRepository.findByPatient(patient);
		model.addAttribute("listMesuresSUP",listMesuresSUP);
		
		List<Mesures> listMesuresINF = mesureInfRepository.findByPatient(patient);
		model.addAttribute("listMesuresINF",listMesuresINF);
		
		model.addAttribute("patientID",id);
		model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		model.addAttribute("pageTitle", "Fiche de mesures");
		
		return "Mesure/index";
	}
	
	// URL-Pattern --> /Patient/1/Mesure/SUP/create
	@RequestMapping(value="/Patient/{id}/Mesure/SUP/create", method=RequestMethod.POST)
	public String saveSUP(@PathVariable(value="id") Long id, Model model, MesureMembreSup mesure)
	{
		//this line set the ID, but not to '0' it triggers the auto ID handlers from mesuresID_generator TABLE
		mesure.setId((long) 0); 
		
		mesure.setPatient(patientRepository.findById(id).get());
		mesure.setTYPE(TypeProthese.SUP);
		mesureSupRepository.save(mesure);
		
		return "redirect:/Patient/"+id+"/Mesure/index";
	}
	
	// URL-Pattern --> /Patient/1/Mesure/INF/create
	@RequestMapping(value="/Patient/{id}/Mesure/INF/create", method=RequestMethod.POST)
	public String saveSUP(@PathVariable(value="id") Long id, Model model, MesureMembreInf mesure)
	{
		//this line set the ID, but not to '0' it triggers the auto ID handlers from mesuresID_generator TABLE
		mesure.setId((long) 0); 
		
		mesure.setPatient(patientRepository.findById(id).get());
		mesure.setTYPE(TypeProthese.INF);
		mesureInfRepository.save(mesure);
		
		return "redirect:/Patient/"+id+"/Mesure/index";
	}
}
