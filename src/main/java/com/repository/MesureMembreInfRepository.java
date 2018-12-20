package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.MesureMembreInf;
import com.entities.Mesures;
import com.entities.Patient;

public interface MesureMembreInfRepository extends JpaRepository<MesureMembreInf, Long>
{
	List<Mesures> findByPatient(Patient patient);

}
