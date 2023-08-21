package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Clienti;

@Service
@Transactional
public class ClientiSerciveImp implements ClientiService{

	@Override
	public List<Clienti> selAllClienti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insCliente(Clienti cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updCliente(Clienti cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Clienti> selClienteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
