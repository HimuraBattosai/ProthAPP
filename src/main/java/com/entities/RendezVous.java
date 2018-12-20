package com.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.CommonBaseModel;

@Entity
public class RendezVous extends CommonBaseModel
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@DateTimeFormat(pattern = "HH:mm")
	private LocalDateTime heure_debut;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalDateTime heure_fin;
	private String sujet;
	private boolean presence;
	private String resultat;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="therapeute_id", nullable=false)
	private Therapeute therapeute;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id", nullable=false)
	private Patient patient;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public RendezVous() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public RendezVous(LocalDate date, LocalDateTime heure_debut, LocalDateTime heure_fin, String sujet, boolean presence, String resultat) {
		super();
		this.date = date;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.sujet = sujet;
		this.presence = presence;
		this.resultat = resultat;
	}

	

	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public LocalDateTime getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(LocalDateTime heure_debut) {
		this.heure_debut = heure_debut;
	}

	public LocalDateTime getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(LocalDateTime heure_fin) {
		this.heure_fin = heure_fin;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public boolean isPresence() {
		return presence;
	}

	public void setPresence(boolean presence) {
		this.presence = presence;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Therapeute getTherapeute() {
		return therapeute;
	}

	public void setTherapeute(Therapeute therapeute) {
		this.therapeute = therapeute;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	

}
