package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.Movimenti;

public interface MovimentiService {

	public List<Movimenti> selAllMovimenti ();
	
	public void insCliente(Movimenti movimento); 
	
	public void updCliente(Movimenti movimento); 
	
	public Optional<Movimenti> selMovimentoById (int id);
	
	public void cancMovimenti(int id);
	
	
}
