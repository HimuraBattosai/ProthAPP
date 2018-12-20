package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Exercice;
import com.entities.ExerciceProg;
import com.entities.Patient;

public interface ExerciceProgRepository extends JpaRepository<ExerciceProg, Long> {
	List<ExerciceProg> findByPatient(Patient patient);
	List<ExerciceProg> findByExercice(Exercice exercice);
}
