package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.CommonBaseModel;
import com.enumeration.Payement;

@Entity
public class Commande extends CommonBaseModel
{
	private double prix;
	
	@Enumerated(EnumType.STRING)
	private Payement methode_payement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="therapeute_id", nullable=false)
	private Therapeute therapeute;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prothese_id", nullable=false)
	private Prothese prothese;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id", nullable=false)
	private Patient patient;

	
	
	
	
	
	
	
	
	
	
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Commande(double prix, Payement methode_payement) {
		super();
		this.prix = prix;
		this.methode_payement = methode_payement;
	}

	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public Payement getMethode_payement() {
		return methode_payement;
	}


	public void setMethode_payement(Payement methode_payement) {
		this.methode_payement = methode_payement;
	}


	public Therapeute getTherapeute() {
		return therapeute;
	}


	public void setTherapeute(Therapeute therapeute) {
		this.therapeute = therapeute;
	}


	public Prothese getProthese() {
		return prothese;
	}


	public void setProthese(Prothese prothese) {
		this.prothese = prothese;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	
	
	
	
	
	
	
}
