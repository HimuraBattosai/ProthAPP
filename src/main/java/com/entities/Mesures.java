package com.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.CommonBaseModel;
import com.enumeration.TypeProthese;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class Mesures
{
	@Id 
	@TableGenerator(name = "MesuresGEN",
			table = "MesureID_Generator",
            pkColumnName = "name",
            valueColumnName = "sequence",
            allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "MesuresGEN")
	private Long id;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	@Enumerated(EnumType.STRING)
	private TypeProthese TYPE;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id", nullable=false)
	private Patient patient;

	
	public Mesures() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public TypeProthese getTYPE() {
		return TYPE;
	}

	public void setTYPE(TypeProthese tYPE) {
		TYPE = tYPE;
	}
	
	
	
	
	
}
