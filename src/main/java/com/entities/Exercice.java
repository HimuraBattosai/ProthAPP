package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.CommonBaseModel;

@Entity
public class Exercice extends CommonBaseModel
{
	private String description;
	private String link;
	
	@OneToMany(mappedBy="exercice")
	private List<ExerciceProg> exerciceList = new ArrayList<>();

	
	
	
	
	
	
	
	
	
	
	
	public Exercice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exercice(String description, String link) {
		super();
		this.description = description;
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<ExerciceProg> getExerciceList() {
		return exerciceList;
	}

	public void setExerciceList(List<ExerciceProg> exerciceList) {
		this.exerciceList = exerciceList;
	}
	
	
	
	
	

}
