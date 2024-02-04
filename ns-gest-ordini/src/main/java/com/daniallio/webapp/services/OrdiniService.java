package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.Clienti;
import com.daniallio.webapp.entities.Ordini;

public interface OrdiniService {

	public List<Ordini> selAllOrdini();
	
	public Optional<Ordini> sellOrdiniByID (String id);
	
	public  Optional<List<Ordini>> selOrdiniByCliente (Clienti cliente);
	
	public void insOrdini(Ordini ordini);
	
	public void updOrdini(Ordini ordini);
	
	public void cancOrdine (int id);
	
}
