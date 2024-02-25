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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Clienti;
import com.daniallio.webapp.entities.ClientiDTO;
import com.daniallio.webapp.entities.Movimenti;
import com.daniallio.webapp.entities.MovimentiDTO;
import com.daniallio.webapp.entities.Ordini;
import com.daniallio.webapp.entities.OrdiniDTO;
import com.daniallio.webapp.services.ClientiService;
import com.daniallio.webapp.services.OrdiniService;

@Controller
@RequestMapping("api/ordini")
public class OrdiniController {

	
	@Autowired
	OrdiniService serviceOrdini;
	
	@Autowired
	ClientiService serviceClienti;
	
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
	
	//ritorna l'elenco degli ordini per un cliente
	@GetMapping(value = "/all/cliente/{cliente}", produces = "application/json")
	public ResponseEntity<List<OrdiniDTO>>getAllOrdiniByCliente(@PathVariable("cliente")String cliente){
		
		logger.info("********Medoto getAllOrdiniByCliente " + cliente);
		
		
		//dato il codice cliente recupero l'entità cliente collegata
		
		Optional<Clienti> clienteOpt = serviceClienti.selClienteByCodice(cliente);
		
		if(clienteOpt.isPresent() ) {
			
			Clienti clienteEntity = clienteOpt.get();
			
			
			//recupero l'eòenco degli ordini per cliente			
			Optional<List<Ordini>> ordini =  serviceOrdini.selOrdiniByCliente(clienteEntity);				
			
			List<OrdiniDTO> ordiniDTO = new ArrayList<OrdiniDTO>();
			
			//se è presente un ordine
			if(ordini.isPresent()) {
				
				//converto in DTO
				
				ordini.get().forEach(s -> ordiniDTO.add(s.ordiniToDTO()));
				
			}
		
			return new ResponseEntity<List<OrdiniDTO>>(ordiniDTO, HttpStatus.OK);
			
		}
		return null;
		
	
			
		
	
		
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
	
	
	//inserimento nuovo ordine
	@PostMapping (value = "/ins", produces = "application/json")
	
	public ResponseEntity<OrdiniDTO>  insOrdine(@RequestBody OrdiniDTO ordine) throws Exception{
		
		
		logger.info("********Medoto insOrdine");
		Optional<Clienti> cliente;
		

		//verifico che esistano l'ordine ed il cliente
		
		
		logger.info(ordine.getCliente());
	
		cliente = serviceClienti.selClienteByCodice(ordine.getCliente());
		
		if(!cliente.isPresent()) {
			
			throw new Exception ("Cliente non corretto");
		}
		
		//preparo l'inserimento dell'ordine
		logger.info("Preparo ordine " + ordine.getKey());
		Ordini ordineIns = new Ordini();
		
		ordineIns.setCliente(cliente.get());
		ordineIns.setKey(ordine.getKey());
		ordineIns.setDescrizione(ordine.getDescrizione());
		ordineIns.setDataOrd(ordine.getDataOrd());
		ordineIns.setOre(ordine.getOre());
		ordineIns.setValore(ordine.getValore());
		
		serviceOrdini.insOrdini(ordineIns);
		
		return new ResponseEntity<OrdiniDTO>(ordine, HttpStatus.OK);		
		
		
	
	}
	
	
	
}
