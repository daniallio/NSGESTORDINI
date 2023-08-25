package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Ordini;
import com.daniallio.webapp.repository.OrdiniRepository;

@Service
@Transactional
public class OrdiniServiceImp implements OrdiniService{

	@Autowired
	OrdiniRepository repo;
	
	@Override
	public List<Ordini> selAllOrdini() {
		
		return repo.findAll();
	}

	@Override
	public Optional<Ordini> sellOrdiniByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insOrdini(Ordini ordini) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updOrdini(Ordini ordini) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancOrdine(int id) {
		// TODO Auto-generated method stub
		
	}

}