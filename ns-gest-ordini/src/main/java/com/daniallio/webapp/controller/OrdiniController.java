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
import com.daniallio.webapp.entities.Ordini;
import com.daniallio.webapp.entities.OrdiniDTO;
import com.daniallio.webapp.services.OrdiniService;

@Controller
@RequestMapping("api/ordini")
public class OrdiniController {

	
	@Autowired
	OrdiniService serviceOrdini;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdiniController.class);
	
	
	//ritorna l'elenco degli ordini
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<OrdiniDTO>>getAllOrdini(){
		
		logger.info("********Medoto getAllOrdini");
		List<Ordini> ordini =  serviceOrdini.selAllOrdini();		
		//converto in DTO
		List<OrdiniDTO> ordiniDTO = new ArrayList<OrdiniDTO>();
		ordini.forEach(s -> ordiniDTO.add(s.ordiniToDTO()));
		
		return new ResponseEntity<List<OrdiniDTO>>(ordiniDTO, HttpStatus.OK);
		
	}
	
}
