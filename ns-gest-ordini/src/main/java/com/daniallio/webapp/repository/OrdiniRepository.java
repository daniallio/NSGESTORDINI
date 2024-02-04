package com.daniallio.webapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniallio.webapp.entities.Clienti;
import com.daniallio.webapp.entities.Ordini;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordini,String>{

	
	Optional<List<Ordini>> findByCliente(Clienti cliente);
	
}
