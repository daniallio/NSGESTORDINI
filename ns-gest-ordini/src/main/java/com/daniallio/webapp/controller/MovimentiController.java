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
import com.daniallio.webapp.services.MovimentiService;
import com.daniallio.webapp.services.OrdiniService;

import net.bytebuddy.implementation.bytecode.Throw;

@Controller
@RequestMapping("api/movimenti")
public class MovimentiController {

	
	@Autowired
	MovimentiService serviceMov;
	
	@Autowired 
	ClientiService serviceCli;
	
	@Autowired
	OrdiniService serviceOrd;
	
	
	
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
	
	//inserisco un nuovo movimento
	@PostMapping (value = "/ins", produces = "application/json")
	public ResponseEntity<MovimentiDTO>  insMovimento (@RequestBody MovimentiDTO movimento) throws Exception{
		
		logger.info("********Medoto insMovimento");
		
	
		
		Optional<Ordini> ordine;
		Optional<Clienti> cliente;
		
		//verifico che esistano l'ordine ed il cliente
		
		
		logger.info(movimento.getCliente());
	
		cliente = serviceCli.selClienteByCodice(movimento.getCliente());
		
		if(!cliente.isPresent()) {
			
			throw new Exception ("Cliente non corrett0");
		}
		
		
		logger.info(movimento.getOrdine());
		
		
		ordine = serviceOrd.sellOrdiniByID(movimento.getOrdine());
		
			if(!ordine.isPresent()) {
			
				throw new Exception ("Ordine non corrett0");
			}
		
		
		logger.info("********Medoto insMovimento trovato ordine");
		
		
		logger.info("********Medoto insMovimento " + ordine.get().getDescrizione());		
		

		
		//creo il movimento da inserire
		
		Movimenti movimentoIns = new Movimenti();
		
		//movimentoIns.setCliente(cliente.get());
		movimentoIns.setOrdine(ordine.get());
		movimentoIns.setOre(movimento.getOre());
		movimentoIns.setNote(movimento.getNote());
		
		serviceMov.insMovimento(movimentoIns);
		
		return new ResponseEntity<MovimentiDTO>(movimento,HttpStatus.OK);
	}
	
}
