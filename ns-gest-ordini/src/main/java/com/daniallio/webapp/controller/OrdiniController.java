package com.daniallio.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Clienti;
import com.daniallio.webapp.entities.ClientiDTO;
import com.daniallio.webapp.entities.Ordini;
import com.daniallio.webapp.entities.OrdiniDTO;
import com.daniallio.webapp.services.ClientiService;
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
	
	
	
	//ritorna ordine per codice
	@GetMapping(value = "/cerca/{codice}", produces = "application/json")
	public ResponseEntity<OrdiniDTO> getClienteByCodice (@PathVariable("codice") String codice){
		
		logger.info("********Medoto getClienteByCodice");
		
		
		Optional<Ordini> ordine =  serviceOrdini.sellOrdiniByID(codice);		
		//converto in DTO
		
		OrdiniDTO ordineDTO = new OrdiniDTO();
		
		//se esite lo converto in DTO altrimenti eccezione
		if(ordine.isPresent()) {
			
			ordineDTO  = ordine.get().ordiniToDTO();
		}
		

		
		return new ResponseEntity<OrdiniDTO>(ordineDTO, HttpStatus.OK);
		
		

	}
}
