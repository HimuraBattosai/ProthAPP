package com.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.enumeration.GrpSanguin;
import com.enumeration.Sexe;
import com.enumeration.TypeProthese;
import com.repository.MesureMembreInfRepository;
import com.repository.MesureMembreSupRepository;

@Entity
@DiscriminatorValue("UP")
public class Patient extends User
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_naissance;
	
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	
	@Enumerated(EnumType.STRING)
	private GrpSanguin grp_sanguin;
	
	private double taille;
	private double poids;
	
	private String accident;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_accident;
	
	@OneToMany(mappedBy="patient", fetch = FetchType.EAGER)
	private Collection<Mesures> mesureList;
	
	@OneToMany(mappedBy="patient")
	private Collection<RendezVous> rdvList;
	
	@OneToMany(mappedBy="patient")
	private Collection<Commande> commandeList;
	
	@OneToMany(mappedBy="patient")
	private Collection<ExerciceProg> exerciceList;
	
	
	public List<Mesures> getMesuresByType(TypeProthese type)
	{
		return this.getMesureList().stream().filter(p -> p.getTYPE().equals(type)).collect(Collectors.toList());
	}
	
	
	

	
	
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String nom, String prenom, String adresse, String telephone, Date date_naissance, Sexe sexe,
			GrpSanguin grp_sanguin, double taille, double poids, String accident, Date date_accident) 
	{
		super(nom, prenom, adresse, telephone);
		this.date_naissance = date_naissance;
		this.sexe = sexe;
		this.grp_sanguin = grp_sanguin;
		this.taille = taille;
		this.poids = poids;
		this.accident = accident;
		this.date_accident = date_accident;
		
	}
	

	public Patient(String username, String password, String nom, String prenom, String adresse, String telephone) {
		super(username, password, nom, prenom, adresse, telephone);
		// TODO Auto-generated constructor stub
	}








	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public GrpSanguin getGrp_sanguin() {
		return grp_sanguin;
	}

	public void setGrp_sanguin(GrpSanguin grp_sanguin) {
		this.grp_sanguin = grp_sanguin;
	}

	public double getTaille() {
		return taille;
	}

	public void setTaille(double taille) {
		this.taille = taille;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getAccident() {
		return accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public Date getDate_accident() {
		return date_accident;
	}

	public void setDate_accident(Date date_accident) {
		this.date_accident = date_accident;
	}

	public Collection<Mesures> getMesureList() {
		return mesureList;
	}

	public void setMesureList(Collection<Mesures> mesureList) {
		this.mesureList = mesureList;
	}

	public Collection<RendezVous> getRdvList() {
		return rdvList;
	}

	public void setRdvList(Collection<RendezVous> rdvList) {
		this.rdvList = rdvList;
	}

	public Collection<Commande> getCommandeList() {
		return commandeList;
	}

	public void setCommandeList(Collection<Commande> commandeList) {
		this.commandeList = commandeList;
	}

	public Collection<ExerciceProg> getExerciceList() {
		return exerciceList;
	}

	public void setExerciceList(Collection<ExerciceProg> exerciceList) {
		this.exerciceList = exerciceList;
	}

	
}
