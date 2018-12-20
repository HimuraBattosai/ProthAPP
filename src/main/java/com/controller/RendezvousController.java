package com.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.MesureMembreSup;
import com.entities.Patient;
import com.entities.ProtheseCatalogue;
import com.entities.RendezVous;
import com.entities.Therapeute;
import com.enumeration.TypeProthese;
import com.repository.PatientRepository;
import com.repository.RendezVousRepository;
import com.repository.TherapeuteRepository;

import utilities.Formatter;

@Controller
public class RendezvousController 
{
	@Autowired PatientRepository patientRepository;
	@Autowired TherapeuteRepository therapeuteRepository;
	@Autowired RendezVousRepository rdvRepository;
	
	
	// URL-Pattern --> /Patient/1/RendezVous/index
	@RequestMapping(value="/Patient/{id}/RendezVous/index", method=RequestMethod.GET)
	public String index(@PathVariable(value="id") Long id, Model model)
	{
		Patient patient = patientRepository.findById(id).get();
		List<RendezVous> listRDV = rdvRepository.findAll();
		
		model.addAttribute("listRDV", listRDV);
		model.addAttribute("patientID",id);
		model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		model.addAttribute("pageTitle", "Consultations");
		
		return "RendezVous/index";
	}
	
	// URL-Pattern --> /Patient/1/RendezVous/create
	@RequestMapping(value="/Patient/{id}/RendezVous/create", method=RequestMethod.POST)
	public String save(Model model, @PathVariable(value="id") Long id, @Valid RendezVous rdv, BindingResult bindingResult,
			@RequestParam(required=false) String heure_debut,
			@RequestParam(required=false) String heure_fin,
			@RequestParam(required=false) String date)
	{
		rdv.setHeure_debut(Formatter.getTimeFromString(date+" "+heure_debut, "yyyy-MM-dd HH:mm"));
		rdv.setHeure_fin(Formatter.getTimeFromString(date+" "+heure_fin, "yyyy-MM-dd HH:mm"));
		
		// THIS PART we fetch THERAPEUTE in a static way, with spring security we will go get LOGGED Therapeute
		Therapeute therapeute = therapeuteRepository.findById((long) 7).get(); 
		
		rdv.setPatient(patientRepository.findById(id).get());
		rdv.setTherapeute(therapeute);
		
		rdvRepository.save(rdv);
		
		return "redirect:/Patient/"+id+"/RendezVous/index";
	}
	
	
	@RequestMapping(value="/RendezVous/{id}")
	public String details(Model model, @PathVariable(value = "id") Long id) 
	{
		RendezVous rdv = rdvRepository.getOne(id);
		System.out.println("pres-->"+rdv.isPresence());
		model.addAttribute("rdv", rdv);
		model.addAttribute("patientName",rdv.getPatient().getNom()+' '+rdv.getPatient().getPrenom());
		model.addAttribute("patientID", rdv.getPatient().getId());
		model.addAttribute("pageTitle", "Details Rendez-Vous");
		
		return "RendezVous/details";
	}
	
	// URL-Pattern --> /Patient/1/RendezVous/create
	@RequestMapping(value="/RendezVous/update/{id}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable(value="id") Long id,
			@RequestParam(required=false) boolean presence,
			@RequestParam(required=false) String resultat)
	{
		RendezVous rdv = rdvRepository.getOne(id);
		rdv.setPresence(presence);
		rdv.setResultat(resultat);
		
		rdvRepository.save(rdv);
		
		return "redirect:/Patient/"+rdv.getPatient().getId()+"/RendezVous/index";
	}

}
