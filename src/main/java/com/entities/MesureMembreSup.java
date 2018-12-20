package com.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.enumeration.TypeProthese;

@Entity
public class MesureMembreSup extends Mesures
{
	private String mesure1;
	private String mesure2;
	
	
	
	
	public MesureMembreSup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MesureMembreSup(String mesure1, String mesure2) {
		super();
		this.mesure1 = mesure1;
		this.mesure2 = mesure2;
	}
	public String getMesure1() {
		return mesure1;
	}
	public void setMesure1(String mesure1) {
		this.mesure1 = mesure1;
	}
	public String getMesure2() {
		return mesure2;
	}
	public void setMesure2(String mesure2) {
		this.mesure2 = mesure2;
	}
	@Override
	public String toString() {
		return "MesureMembreSup [mesure1=" + mesure1 + ", mesure2=" + mesure2 + "]";
	}
	
	
	
	
	
}
