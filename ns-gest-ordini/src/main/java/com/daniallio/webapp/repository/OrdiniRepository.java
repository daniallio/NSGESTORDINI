package com.daniallio.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniallio.webapp.entities.Ordini;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordini,Integer>{

}
