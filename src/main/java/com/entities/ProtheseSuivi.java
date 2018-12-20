package com.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.CommonBaseModel;

@Entity
public class ProtheseSuivi extends CommonBaseModel 
{
	private double temperature;
	private String alignement;
	private String etat_cartillage;
	private String lesion;
	private String micro_mouvement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prothese_id", nullable=false)
	private Prothese prothese;

	
	
	
	
	
	
	
	
	
	
	
	
	public ProtheseSuivi() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProtheseSuivi(double temperature, String alignement, String etat_cartillage, String lesion,
			String micro_mouvement) {
		super();
		this.temperature = temperature;
		this.alignement = alignement;
		this.etat_cartillage = etat_cartillage;
		this.lesion = lesion;
		this.micro_mouvement = micro_mouvement;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public String getAlignement() {
		return alignement;
	}


	public void setAlignement(String alignement) {
		this.alignement = alignement;
	}


	public String getEtat_cartillage() {
		return etat_cartillage;
	}


	public void setEtat_cartillage(String etat_cartillage) {
		this.etat_cartillage = etat_cartillage;
	}


	public String getLesion() {
		return lesion;
	}


	public void setLesion(String lesion) {
		this.lesion = lesion;
	}


	public String getMicro_mouvement() {
		return micro_mouvement;
	}


	public void setMicro_mouvement(String micro_mouvement) {
		this.micro_mouvement = micro_mouvement;
	}


	public Prothese getProthese() {
		return prothese;
	}


	public void setProthese(Prothese prothese) {
		this.prothese = prothese;
	}
	
	
	
	

}
