package com.entities;

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
public class ExerciceProg extends CommonBaseModel
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_heure;
	private int nb_repetition;
	private int duree_minutes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="therapeute_id")
	private Therapeute therapeute;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="exercice_id")
	private Exercice exercice;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ExerciceProg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExerciceProg(Date date_heure, int nb_repetition, int duree_minutes) {
		super();
		this.date_heure = date_heure;
		this.nb_repetition = nb_repetition;
		this.duree_minutes = duree_minutes;
	}

	public Date getDate_heure() {
		return date_heure;
	}

	public void setDate_heure(Date date_heure) {
		this.date_heure = date_heure;
	}

	public int getNb_repetition() {
		return nb_repetition;
	}

	public void setNb_repetition(int nb_repetition) {
		this.nb_repetition = nb_repetition;
	}

	public int getDuree_minutes() {
		return duree_minutes;
	}

	public void setDuree_minutes(int duree_minutes) {
		this.duree_minutes = duree_minutes;
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

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}
	
	
	

}
