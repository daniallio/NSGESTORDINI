package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Clienti;
import com.daniallio.webapp.entities.Movimenti;
import com.daniallio.webapp.repository.MovimentiRepository;
@Service
@Transactional
public class MovimentiServiceImp implements MovimentiService{

	@Autowired
	MovimentiRepository repo;
	
	

	@Override
	public List<Movimenti> selAllMovimenti() {
		
		return repo.findAll();
	}

	@Override
	public void insCliente(Movimenti movimento) {
		
		repo.save(movimento);
		
	}

	@Override
	public void updCliente(Movimenti movimento) {
		repo.save(movimento);
		
	}

	@Override
	public Optional<Movimenti> selMovimentoById(int id) {
		return repo.findById(id);
	}

	@Override
	public void cancMovimenti(int id) {
		// TODO Auto-generated method stub
		
	}

}
