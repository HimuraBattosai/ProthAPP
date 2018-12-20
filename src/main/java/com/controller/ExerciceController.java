package com.controller;

import java.util.List;
import java.util.Optional;

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

import com.entities.Exercice;
import com.entities.ExerciceProg;
import com.entities.Patient;
import com.repository.ExerciceProgRepository;
import com.repository.ExerciceRepository;
import com.repository.PatientRepository;


@Controller
public class ExerciceController 
{
	@Autowired
	ExerciceRepository exerciceRepository;
	@Autowired
	ExerciceProgRepository exerciceProgRepository;
	@Autowired
	PatientRepository patientRepository;
	
	
	@RequestMapping(value= {"/Exercice","/Exercice/index","/Patient/{id}/Exercice"})
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="size",defaultValue="5")int s,
			@RequestParam(name="motCle",defaultValue="")String mc,
			@PathVariable(value="id") Optional<Long> id) 
	{
		Page<Exercice> pageExercice= exerciceRepository.chercher("%"+mc+"%",PageRequest.of(p,s));
		model.addAttribute("listExercice", pageExercice.getContent());
		int[] pages=new int[pageExercice.getTotalPages()];
		model.addAttribute("pages", pages);
		
		if (id.isPresent()) 
		{
			model.addAttribute("patientID",id.get());
			Patient patient = patientRepository.findById(id.get()).get();
			model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		}
		else model.addAttribute("patientID",null);
		
		model.addAttribute("pageTitle", "Exercices");
		return "Exercice/Index";
	}
	
	@RequestMapping(value="/Exercice/{id}")
	public String details(Model model, @PathVariable(value = "id") Long id) 
	{
		Exercice exercice = exerciceRepository.getOne(id);
		
		model.addAttribute("exercice", exercice);
		/*model.addAttribute("patientName",exercice.getNom()+' '+exercice.getPrenom());
		model.addAttribute("patientID",id);
		model.addAttribute("pageTitle", "Fiche du patient");*/
		return "Exercice/Details";
	}
	
	@RequestMapping(value="/Exercice/Delete")
	public String delete(Long id) 
	{
		exerciceRepository.deleteById(id);
		return "redirect:/Exercice";
		
	}
	
	@RequestMapping(value="/Exercice/create", method=RequestMethod.GET)
	public String create(Model model)
	{
		model.addAttribute("pageTitle", "Nouveau exercice");
		model.addAttribute("exercice", new Exercice());
		return "Exercice/Create";
	}
	
	@RequestMapping(value="/Exercice/create", method=RequestMethod.POST)
	public String save(Model model,@Valid Exercice exercice, BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) {
			return "Exercice/Create";
		}
		
		exerciceRepository.save(exercice);
		model.addAttribute("exercice", exercice);
		
		return "redirect:/Exercice/"+exercice.getId();
	}
	
	@RequestMapping(value="/Exercice/Edit",method=RequestMethod.GET)
	public String edit(Model model, Long id) {
		Exercice exercice= exerciceRepository.getOne(id);
		model.addAttribute("exercice", exercice);
		model.addAttribute("pageTitle", "Modifier exercice");
		return "Exercice/Edit";
	}

}
