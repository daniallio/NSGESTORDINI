package com.daniallio.webapp.entities;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "MOVIMENTI")
@Data
public class Movimenti {

	
	@Id
	@Column(name = "mov_code", nullable = false, updatable = false, insertable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@Column(name = "mov_note")
	@Basic(optional = false)
	private String note;
		
	@Column(name = "mov_mese")
	@Basic(optional = false)
	private int mese;	
	
	@Column(name = "mov_hour")
	@Basic(optional = false)
	private double ore;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude // per problema su loombook
	@JoinColumn(name = "mov_cli", referencedColumnName = "cli_key") // name è il nome della colonna FK, reference.. nome // della chiave primaria nella tab collegata																	
	@JsonBackReference(value = "movimento") // punto di arrivo che non verrà mostrato se estraggo il json della classe
	private Clienti cliente;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude // per problema su loombook
	@JoinColumn(name = "mov_ord", referencedColumnName = "ord_code") // name è il nome della colonna FK, reference.. nome // della chiave primaria nella tab collegata																	
	@JsonBackReference(value = "movimento") // punto di arrivo che non verrà mostrato se estraggo il json della classe
	private Ordini ordine;
	
	
	
	public MovimentiDTO movimentoToDTO() {
		
		MovimentiDTO movimentoDTO = new MovimentiDTO();
		
		movimentoDTO.setKey(this.key);
		movimentoDTO.setNote(this.note);
		movimentoDTO.setMese(this.mese);
		movimentoDTO.setOre(this.ore);
		movimentoDTO.setOrdine(this.ordine.getKey());
		movimentoDTO.setCliente(this.cliente.getCodiceCliente());
		
	
		
		return movimentoDTO;
		
	}
	
}
