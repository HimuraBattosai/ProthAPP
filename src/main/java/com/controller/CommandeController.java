package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Commande;
import com.entities.MesureMembreInf;
import com.entities.MesureMembreSup;
import com.entities.Mesures;
import com.entities.Patient;
import com.entities.Prothese;
import com.entities.ProtheseCatalogue;
import com.entities.Therapeute;
import com.enumeration.Payement;
import com.enumeration.TypeProthese;
import com.repository.CommandeRepository;
import com.repository.MesureMembreInfRepository;
import com.repository.MesureMembreSupRepository;
import com.repository.MesuresRepository;
import com.repository.PatientRepository;
import com.repository.ProtheseCatalogueRepository;
import com.repository.ProtheseRepository;
import com.repository.TherapeuteRepository;

@Controller
public class CommandeController 
{
	@Autowired CommandeRepository commandeRepository;
	@Autowired ProtheseCatalogueRepository protheseCatlogueRepository;
	@Autowired PatientRepository patientRepository;
	@Autowired MesuresRepository mesureRepository;
	@Autowired MesureMembreInfRepository mesureInfRepository;
	@Autowired MesureMembreSupRepository mesureSupRepository;
	@Autowired TherapeuteRepository therapeuteRepository;
	@Autowired ProtheseRepository protheseRepository;
	
	// URL-Pattern --> /Patient/1/Commande/index
	@RequestMapping(value= {"/Patient/{id}/Commande/index","/Commande/index"}, method=RequestMethod.GET)
	public String index(@PathVariable(value="id", required=false) Long id, Model model)
	{
		List<Commande> listCommandes;
		if(id!=null)
		{
			Patient patient = patientRepository.findById(id).get();
			listCommandes = commandeRepository.findByPatient(patient);
			model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		}
		else 
		{
			listCommandes = commandeRepository.findAll();
		}
		
		model.addAttribute("patientID",id);
		model.addAttribute("pageTitle", "Commandes");
		model.addAttribute("listCommandes", listCommandes);
		
		return "Commande/index";
	}
		
	@RequestMapping(value="/Commande/{id}")
	public String details(Model model, @PathVariable(value = "id") Long id) 
	{
		Commande commande = commandeRepository.getOne(id);
		
		model.addAttribute("commande", commande);
		model.addAttribute("patientName",commande.getPatient().getNom()+' '+commande.getPatient().getPrenom());
		model.addAttribute("patientID", commande.getPatient().getId());
		model.addAttribute("pageTitle", "Details commande");
		return "Commande/details";
	}
	
	// URL-Pattern --> /Commande/create?patientID=1&protheseID=2
	@RequestMapping(value="/Commande/create", method=RequestMethod.GET)
	public String create(Model model, @RequestParam(required=false) Long patientID, @RequestParam(required=false) Long protheseID)
	{
		List<ProtheseCatalogue> listProtheses = protheseCatlogueRepository.findAll();
		List<Patient> listPatients = patientRepository.findAll();
		
		ProtheseCatalogue prothese = new ProtheseCatalogue();
		if(protheseID!=null) prothese = protheseCatlogueRepository.findById(protheseID).get();
		
		Patient patient = new Patient();
		List<Mesures> listMesures = null;
		if(patientID!=null) 
		{
			patient = patientRepository.findById(patientID).get();
			listMesures = patient.getMesuresByType(prothese.getType());
		}
		List<Payement> listPayements = Arrays.asList(Payement.values());
		
		model.addAttribute("patient", patient);
		model.addAttribute("listPatients", listPatients);
		model.addAttribute("prothese", prothese);
		model.addAttribute("listProtheses", listProtheses);
		model.addAttribute("listPayements", listPayements);
		model.addAttribute("listMesures", listMesures);
		
		model.addAttribute("pageTitle", "Nouvelle commande");
		model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		model.addAttribute("patientID",patient.getId());
		return "Commande/create";
	}
	
	@RequestMapping(value="/Commande/create", method=RequestMethod.POST)
	public String save(Model model, Commande commande, 
			@RequestParam(required=false) Long patientID, 
			@RequestParam(required=false) Long protheseID,
			@RequestParam(required=false) Long mesureID)
	{ 
		Prothese prothese = new Prothese();
		prothese.setProthese_details(protheseCatlogueRepository.findById(protheseID).get());
		prothese.setMesure(mesureRepository.findById(mesureID).get());
		protheseRepository.save(prothese);
		
		// THIS PART we fetch THERAPEUTE in a static way, with spring security we will go get LOGGED Therapeute
		Therapeute therapeute = therapeuteRepository.findById((long) 7).get();
		 
		Patient patient = patientRepository.findById(patientID).get();
		
		commande.setPatient(patient);
		commande.setProthese(prothese);
		commande.setTherapeute(therapeute);
		
		commandeRepository.save(commande);

		 return "redirect:/Commande/"+commande.getId();
	}
	
	
}
