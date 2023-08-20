package com.daniallio.webapp.entities;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ORDINI")
@Data


public class Ordini {

	
	@Id
	@Column(name = "ord_code", nullable = false, updatable = false, insertable = false)
	private int key;
	
	@Column(name = "ord_desc")
	@Basic(optional = false)
	private String descrizione;
	
	@Column(name = "ord_val")
	@Basic(optional = false)
	private double valore;
	
	@Column(name = "ord_hours")
	@Basic(optional = false)
	private double ore;
	
	@Column(name = "ord_res_hours")
	@Basic(optional = false)
	private double oreResidue;
	

	@Column(name = "ord_res_val")
	@Basic(optional = false)
	private double valResiduo;
	
	@Column(name = "ord_date")
	@Temporal(TemporalType.DATE)
	private Date dataOrd;
	
	
	@ManyToOne
	@EqualsAndHashCode.Exclude //per problema su loombook
	@JoinColumn(name = "ord_cli",referencedColumnName = "cli_key") //name è il nome della colonna FK, reference.. nome della chiave primaria nella tab collegata
	@JsonBackReference (value="ordine") //punto di arrivo che non verrà mostrato se estraggo il json della classe
	private Clienti cliente;
	
}
