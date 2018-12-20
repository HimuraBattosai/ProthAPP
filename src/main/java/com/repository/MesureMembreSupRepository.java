package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.MesureMembreSup;
import com.entities.Mesures;
import com.entities.Patient;

public interface MesureMembreSupRepository extends JpaRepository<MesureMembreSup, Long>
{
	List<Mesures> findByPatient(Patient patient);
}
