package com.daniallio.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniallio.webapp.entities.Movimenti;

@Repository
public interface MovimentiRepository extends JpaRepository<Movimenti, Integer>{

	
	
	
	
}
