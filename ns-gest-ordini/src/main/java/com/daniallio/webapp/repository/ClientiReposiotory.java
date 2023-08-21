package com.daniallio.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniallio.webapp.entities.Clienti;

@Repository
public interface ClientiReposiotory extends JpaRepository<Clienti, Integer>{

}
