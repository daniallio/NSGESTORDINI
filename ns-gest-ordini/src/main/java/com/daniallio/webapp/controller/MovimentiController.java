package com.daniallio.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Clienti;
import com.daniallio.webapp.entities.ClientiDTO;
import com.daniallio.webapp.entities.Movimenti;
import com.daniallio.webapp.entities.MovimentiDTO;
import com.daniallio.webapp.entities.Ordini;
import com.daniallio.webapp.entities.OrdiniDTO;
import com.daniallio.webapp.services.MovimentiService;
import com.daniallio.webapp.services.OrdiniService;

@Controller
@RequestMapping("api/movimenti")
public class MovimentiController {

	
	@Autowired
	MovimentiService serviceMov;
	
	private static final Logger logger = LoggerFactory.getLogger(MovimentiController.class);
	
	
	//ritorna l'elenco degli ordini
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<MovimentiDTO>>getAllMovimenti(){
		
		logger.info("********Medoto getAllMovimenti");
		List<Movimenti> movimenti =  serviceMov.selAllMovimenti();		
		//converto in DTO
		List<MovimentiDTO> movimentiDTO = new ArrayList<MovimentiDTO>();
		movimenti.forEach(s -> movimentiDTO.add(s.movimentoToDTO()));
		
		return new ResponseEntity<List<MovimentiDTO>>(movimentiDTO, HttpStatus.OK);
		
	}
	
}
