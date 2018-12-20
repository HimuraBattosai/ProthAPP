package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("UT")
public class Therapeute extends User
{
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RendezVous> rdv = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Commande> commande = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExerciceProg> exercice = new ArrayList<>();
	
	

	
	
	
	
	
	
	
	
	
	
	
	public Therapeute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Therapeute(String nom, String prenom, String adresse, String telephone) {
		super(nom, prenom, adresse, telephone);
	}

	public List<RendezVous> getRdv() {
		return rdv;
	}

	public void setRdv(List<RendezVous> rdv) {
		this.rdv = rdv;
	}

	public List<Commande> getCommande() {
		return commande;
	}

	public void setCommande(List<Commande> commande) {
		this.commande = commande;
	}

	public List<ExerciceProg> getExercice() {
		return exercice;
	}

	public void setExercice(List<ExerciceProg> exercice) {
		this.exercice = exercice;
	}
	
	
	
	
	
	
	
}
