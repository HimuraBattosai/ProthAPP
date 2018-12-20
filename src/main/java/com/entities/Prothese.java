package com.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.CommonBaseModel;
import com.enumeration.Expedition;

@Entity
public class Prothese extends CommonBaseModel
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateProd_lancement;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateProd_fin;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_expedition;
	
	@Enumerated(EnumType.STRING)
	private Expedition type_expedition;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_livraison;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="prothese")
	private Commande commande;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pcatalogue_id", nullable=false)
	private ProtheseCatalogue prothese_details;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="prothese")
	private Collection<ProtheseSuivi> historique_suivi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mesure_id", nullable=false)
	private Mesures mesure;
	
	
	
	
	
	
	
	
	public Prothese() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Prothese(Date dateProd_lancement, Date dateProd_fin, Date date_expedition, Expedition type_expedition,
			Date date_livraison) 
	{
		super();
		this.dateProd_lancement = dateProd_lancement;
		this.dateProd_fin = dateProd_fin;
		this.date_expedition = date_expedition;
		this.type_expedition = type_expedition;
		this.date_livraison = date_livraison;
	}

	public Date getDateProd_lancement() {
		return dateProd_lancement;
	}

	public void setDateProd_lancement(Date dateProd_lancement) {
		this.dateProd_lancement = dateProd_lancement;
	}

	public Date getDateProd_fin() {
		return dateProd_fin;
	}

	public void setDateProd_fin(Date dateProd_fin) {
		this.dateProd_fin = dateProd_fin;
	}

	public Date getDate_expedition() {
		return date_expedition;
	}

	public void setDate_expedition(Date date_expedition) {
		this.date_expedition = date_expedition;
	}

	public Expedition getType_expedition() {
		return type_expedition;
	}

	public void setType_expedition(Expedition type_expedition) {
		this.type_expedition = type_expedition;
	}

	public Date getDate_livraison() {
		return date_livraison;
	}

	public void setDate_livraison(Date date_livraison) {
		this.date_livraison = date_livraison;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public ProtheseCatalogue getProthese_details() {
		return prothese_details;
	}

	public void setProthese_details(ProtheseCatalogue prothese_details) {
		this.prothese_details = prothese_details;
	}

	public Collection<ProtheseSuivi> getHistorique_suivi() {
		return historique_suivi;
	}

	public void setHistorique_suivi(Collection<ProtheseSuivi> historique_suivi) {
		this.historique_suivi = historique_suivi;
	}

	public Mesures getMesure() {
		return mesure;
	}

	public void setMesure(Mesures mesure) {
		this.mesure = mesure;
	}
	

	public String getState()
	{
		if(dateProd_lancement==null) return "En attente";
		if(dateProd_fin==null) return "Production en cours";
		if(date_expedition==null) return "En attente d'expedition";
		if(date_livraison==null) return "Livraison en cours";
		
		return "Livré";
	}
	

}
