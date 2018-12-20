package com.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.ProtheseCatalogue;

public interface ProtheseCatalogueRepository extends JpaRepository<ProtheseCatalogue, Long>
{
	@Query("select c from ProtheseCatalogue c where c.reference like :x")
	public Page<ProtheseCatalogue> chercher(@Param("x")String mc,Pageable pageable);
}
