package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.CommonBaseModel;
import com.enumeration.GrpSanguin;
import com.enumeration.TypeProthese;

@Entity
public class ProtheseCatalogue extends CommonBaseModel
{
	private String reference;
	
	@Enumerated(EnumType.STRING)
	private TypeProthese type;
	
	private String descriptif;
	private double prix;
	
	@OneToMany(mappedBy="prothese_details")
	private List<Prothese> protheseList = new ArrayList<>();

	
	
	
	
	
	
	
	
	
	public ProtheseCatalogue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProtheseCatalogue(String reference, TypeProthese type, String descriptif, double prix) {
		super();
		this.reference = reference;
		this.type = type;
		this.descriptif = descriptif;
		this.prix = prix;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}


	public TypeProthese getType() {
		return type;
	}

	public void setType(TypeProthese type) {
		this.type = type;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public List<Prothese> getProtheseList() {
		return protheseList;
	}

	public void setProtheseList(List<Prothese> protheseList) {
		this.protheseList = protheseList;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	

	
	
	
	
}
