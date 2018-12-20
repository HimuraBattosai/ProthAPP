package com.controller;

import java.awt.print.Pageable;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.atomic.LongAccumulator;

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
import com.entities.ProtheseCatalogue;
import com.repository.PatientRepository;
import com.repository.ProtheseCatalogueRepository;

@Controller
public class ProtheseCatalogueController 
{
	@Autowired private ProtheseCatalogueRepository protheseCatalogueRepository;
	@Autowired private PatientRepository patientRepository;
	
	@RequestMapping(value="/ProtheseCatalogue/details")
	public String detail(Model model, Long id) 
	{
		ProtheseCatalogue protheseCatalogue = protheseCatalogueRepository.getOne(id);
		
		model.addAttribute("pageTitle", "Modele Prothese");
		model.addAttribute("protheseCatalogue", protheseCatalogue);
		return "ProtheseCatalogue/Details";
	}
	
	
	
	@RequestMapping(value="/ProtheseCatalogue/create",method=RequestMethod.GET)
	public String create(Model model) 
	{
		model.addAttribute("pageTitle", "Creer Prothese");
		model.addAttribute("protheseCatalogue", new ProtheseCatalogue());
		return "ProtheseCatalogue/Create";
	}
	
	@RequestMapping(value="/ProtheseCatalogue/save", method=RequestMethod.POST)
	public String save(Model model,@Valid ProtheseCatalogue protheseCatalogue, BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) return "ProtheseCatalogue/Create";
		
		protheseCatalogueRepository.save(protheseCatalogue);
		model.addAttribute("protheseCatalogue", protheseCatalogue);
		
		return "redirect:/ProtheseCatalogue/details?id="+protheseCatalogue.getId();
	}
	
	
	
	@RequestMapping(value="/ProtheseCatalogue/edit",method=RequestMethod.GET)
	public String edit(Model model, Long id) 
	{
		model.addAttribute("pageTitle", "Modifier Prothese");
		ProtheseCatalogue protheseCatalogue= protheseCatalogueRepository.getOne(id);
		model.addAttribute("protheseCatalogue", protheseCatalogue);
		
		return "ProtheseCatalogue/Edit";
	}
	
	@RequestMapping(value="/ProtheseCatalogue/delete")
	public String delete(Long id) 
	{
		protheseCatalogueRepository.deleteById(id);
		return "redirect:/ProtheseCatalogue";
		
	}

	@RequestMapping(value= {"/ProtheseCatalogue/index","/ProtheseCatalogue","/Patient/{id}/Catalogue"})
	public String index(Model model, @RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="size",defaultValue="5")int s,
			@RequestParam(name="motCle",defaultValue="")String mc,
			@PathVariable(value="id") Optional<Long> id) 
	{
		Page<ProtheseCatalogue> pageProthese= protheseCatalogueRepository.chercher("%"+mc+"%", PageRequest.of(p,s));
		model.addAttribute("listProthese", pageProthese.getContent());
		int[] pages=new int[pageProthese.getTotalPages()];
		model.addAttribute("pages", pages);
		
		if (id.isPresent()) 
		{
			model.addAttribute("patientID",id.get());
			Patient patient = patientRepository.findById(id.get()).get();
			model.addAttribute("patientName",patient.getNom()+' '+patient.getPrenom());
		}
		else model.addAttribute("patientID",null);
		
		System.out.println(id.isPresent());
		
		model.addAttribute("pageTitle", "Catalogue");
		return "ProtheseCatalogue/Index";
	}
}
