package com.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.Patient;
import com.entities.Prothese;
import com.enumeration.Expedition;
import com.enumeration.Payement;
import com.repository.PatientRepository;
import com.repository.ProtheseRepository;

@Controller
public class ProtheseController 
{
	@Autowired ProtheseRepository protheseRepository;
	@Autowired PatientRepository patientRepository;
	
	// URL-Pattern --> /Prothese/edit/1
	@RequestMapping(value="/Prothese/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable(value="id") Long id, Model model)
	{
		Prothese prothese = protheseRepository.findById(id).get();
		Patient patient = patientRepository.findById(prothese.getCommande().getPatient().getId()).get();
		
		model.addAttribute("prothese",prothese);
		List<Expedition> typesExpedition = Arrays.asList(Expedition.values());
		model.addAttribute("typesExpedition",typesExpedition);
		
		model.addAttribute("patientID",patient.getId());
		model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		model.addAttribute("pageTitle", "Etat Prothese - SERVICE PRODUCTION");
		return "Prothese/edit";
	}
	
	@RequestMapping(value="/Prothese/edit", method=RequestMethod.POST)
	public String update(Model model,@Valid Prothese prothese, BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) {
			return "Prothese/edit";
		}
		Prothese protheseToUpdate = protheseRepository.getOne(prothese.getId());
		protheseToUpdate.setDate_livraison(prothese.getDate_livraison());
		protheseToUpdate.setDateProd_lancement(prothese.getDateProd_lancement());
		protheseToUpdate.setDateProd_fin(prothese.getDateProd_fin());
		protheseToUpdate.setDate_expedition(prothese.getDate_expedition());
		protheseToUpdate.setType_expedition(prothese.getType_expedition());
		
		protheseRepository.save(protheseToUpdate);
		
		return "redirect:/Commande/"+protheseToUpdate.getCommande().getId();
	}
	

}
