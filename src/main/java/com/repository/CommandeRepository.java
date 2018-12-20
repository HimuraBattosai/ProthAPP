package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Commande;
import com.entities.Patient;

public interface CommandeRepository extends JpaRepository<Commande, Long>
{
	List<Commande> findByPatient(Patient patient);
}
