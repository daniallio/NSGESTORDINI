package com.daniallio.webapp.services;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Clienti;

import com.daniallio.webapp.repository.ClientiRepository;

@Service
@Transactional
public class ClientiSerciveImp implements ClientiService{

	
	@Autowired
	ClientiRepository repo;
	
	@Override
	public List<Clienti> selAllClienti() {
		
		return repo.findAll();
	}

	@Override
	public void insCliente(Clienti cliente) {
		
		repo.save(cliente);
		
	}

	@Override
	public void updCliente(Clienti cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Clienti> selClienteById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void cancCliente(int id) {
		
		repo.deleteById(id);
		
	}

	@Override
	public Optional<Clienti> selClienteByCodice(String codice) {
		
		return repo.findByCodiceCliente(codice);
	}

}
