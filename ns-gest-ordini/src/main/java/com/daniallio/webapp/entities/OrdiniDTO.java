package com.daniallio.webapp.entities;

import java.util.Date;

import lombok.Data;

@Data
public class OrdiniDTO {

	
	private String key;
	
	private String descrizione;
	
	private double valore;
	
	private double ore;
	
	private double oreResidue;
	
	private double valResiduo;
	
	private Date dataOrd;
	
	private String cliente;
	
}
