package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.Clienti;


public interface ClientiService {

	
	public List<Clienti> selAllClienti();
	
	public void insCliente(Clienti cliente); 
	
	public void updCliente(Clienti cliente); 
	
	public Optional<Clienti> selClienteById (int id);
	
}
