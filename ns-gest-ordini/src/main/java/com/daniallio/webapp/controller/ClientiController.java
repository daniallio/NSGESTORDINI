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
import com.daniallio.webapp.services.ClientiService;

@Controller
@RequestMapping("api/clienti")
public class ClientiController {

@Autowired
ClientiService serviceClienti;

private static final Logger logger = LoggerFactory.getLogger(ClientiController.class);


//ritorna l'elenco dei clienti
@GetMapping(value = "/all", produces = "application/json")
public ResponseEntity<List<ClientiDTO>> getAllClienti (){
	
	logger.info("********Medoto getAllClienti");
	
	
	List<Clienti> clienti =  serviceClienti.selAllClienti();		
	//converto in DTO
	List<ClientiDTO> clientiDTO = new ArrayList<ClientiDTO>();
	clienti.forEach(s -> clientiDTO.add(s.clientiToDTO()));
	
	return new ResponseEntity<List<ClientiDTO>>(clientiDTO, HttpStatus.OK);
	
	

}


}
