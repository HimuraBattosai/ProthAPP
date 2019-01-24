package com.controller;

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

import com.entities.Exercice;
import com.entities.ExerciceProg;
import com.entities.Patient;
import com.entities.Therapeute;
import com.repository.ExerciceProgRepository;
import com.repository.ExerciceRepository;
import com.repository.PatientRepository;
import com.repository.TherapeuteRepository;

@Controller

public class ExerciceProgController {
	
	@Autowired PatientRepository patientRepository;
	@Autowired ExerciceProgRepository exerciceProgRepository;
	@Autowired ExerciceRepository exerciceRepository;
	@Autowired TherapeuteRepository therapeuteRepository;
	
	
	// URL-Pattern --> /Patient/1/Commande/index
		@RequestMapping(value= {"/Patient/{id}/ExerciceProg/index","/ExerciceProg/Index"}, method=RequestMethod.GET)
		public String index(@PathVariable(value="id", required=false) Long id, Model model)
		{
			List<ExerciceProg> listExerciceProgs;
			if(id!=null)
			{
				Patient patient = patientRepository.findById(id).get();
				listExerciceProgs = exerciceProgRepository.findByPatient(patient);
				model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
			}
			else 
			{
				listExerciceProgs = exerciceProgRepository.findAll();
			}
			
			model.addAttribute("patientID",id);
			model.addAttribute("pageTitle", "Exercice Programs");
			model.addAttribute("listExerciceProgs", listExerciceProgs);
			
			return "ExerciceProg/index";
		}
		
		@RequestMapping(value="/ExerciceProg/{id}")
		public String details(Model model, @PathVariable(value = "id") Long id) 
		{
			ExerciceProg exercice = exerciceProgRepository.getOne(id);
			
			model.addAttribute("exerciceProg", exercice);
			model.addAttribute("patientName",exercice.getPatient().getNom()+' '+exercice.getPatient().getPrenom());
			model.addAttribute("patientID", exercice.getPatient().getId());
			model.addAttribute("pageTitle", "Details programme d'exercice");
			return "ExerciceProg/details";
		}
		
		// URL-Pattern --> /Commande/create?patientID=1&protheseID=2
		@RequestMapping(value="/ExerciceProg/create", method=RequestMethod.GET)
		public String create(Model model, @RequestParam(required=false) Long patientID, @RequestParam(required=false) Long exerciceID)
		{
			
			
			
			Exercice exercice =new Exercice();
			if(exerciceID!=null) exercice = exerciceRepository.findById(exerciceID).get();
			
			Patient patient = new Patient();
			if(patientID!=null) 
			{
				patient = patientRepository.findById(patientID).get();
			}
			
			model.addAttribute("patient", patient);

			
			
			model.addAttribute("pageTitle", "Nouveau programme d'exercice");
			model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
			model.addAttribute("exercice",exercice);
			model.addAttribute("patientID",patient.getId());
			
			model.addAttribute("exerciceProg", new ExerciceProg());
			return "ExerciceProg/create";
		}
		
		@RequestMapping(value="/ExerciceProg/create", method=RequestMethod.POST)
		public String save(Model model,
				@Valid ExerciceProg exerciceProg,
				BindingResult bindingResult, 
				@RequestParam(required=false) Long patientID,
				@RequestParam(required=false) Long exerciceID)
		{ 
			System.out.println(exerciceProg.getDate_heure());
			if(bindingResult.hasErrors()) return "ExerciceProg/index";
			
			// THIS PART we fetch THERAPEUTE in a static way, with spring security we will go get LOGGED Therapeute
			Therapeute therapeute = therapeuteRepository.findById((long) 7).get();
			
			Patient patient = patientRepository.findById(patientID).get();
			Exercice exercice = exerciceRepository.findById(exerciceID).get();
			
			exerciceProg.setPatient(patient);
			exerciceProg.setExercice(exercice);
			exerciceProg.setTherapeute(therapeute);
			
			exerciceProgRepository.save(exerciceProg);

			 return "redirect:/ExerciceProg/index";//+exerciceProg.getId();
		}
}
