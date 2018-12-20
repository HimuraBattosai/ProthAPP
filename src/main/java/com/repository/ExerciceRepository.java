package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.entities.Exercice;


public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
	@Query("select e from Exercice e where e.description like :x")
	public Page<Exercice> chercher(@Param("x")String mc,Pageable pageable);
}
