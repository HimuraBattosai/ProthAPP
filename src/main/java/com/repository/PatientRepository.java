package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Patient;
import com.entities.User;

public interface PatientRepository extends JpaRepository<Patient, Long>
{
	@Query("select p from Patient p where p.nom like :x")
	public Page<Patient> chercher(@Param("x")String mc,Pageable pageable);
	
	//@Query("select p from Patient p where p.username = ?1")
	   public Patient findByUsername(String username);
}
