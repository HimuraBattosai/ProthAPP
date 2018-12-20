package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Mesures;
import com.entities.Patient;

public interface MesuresRepository extends JpaRepository<Mesures, Long>
{
	List<Mesures> findByPatient(Patient patient);
}
