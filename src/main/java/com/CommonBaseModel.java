package com;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
// This base Model contains ID, created_at and updated_at

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonBaseModel implements Serializable
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	  
	  
	protected CommonBaseModel() {
	  // Protected constructor to prevent direct instantiation.
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
  

}

