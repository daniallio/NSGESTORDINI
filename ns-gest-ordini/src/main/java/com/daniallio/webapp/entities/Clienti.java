package com.daniallio.webapp.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "CLIENTI")
@Data

public class Clienti {

	@Id
	@Column(name = "cli_key", nullable = false, updatable = false, insertable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int key;
	
	@Column(name = "cli_code")
	@Basic(optional = false)
	private String codiceCliente;
	
	@Column(name = "cli_desc")
	@Basic(optional = false)
	private String descrizione;
	
	
	// relazione una molti con la tabella ORDINI
	//un cliente può avere più ordini
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference (value="ordine")// solo quando il webservice resituisce un json, punto di partenza nella classe	 colllegata invece inserisco JSONBACK
	private Set<Ordini> ordine = new HashSet();
	
	// relazione una molti con la tabella MOVIMENTI
	//un cliente può avere più movimenti
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference (value="movimento")// solo quando il webservice resituisce un json, punto di partenza nella classe	 colllegata invece inserisco JSONBACK
	private Set<Movimenti> movimenti = new HashSet();
	
	
	
	public ClientiDTO clientiToDTO() {
		
		ClientiDTO clienteDTO = new ClientiDTO();
		
		clienteDTO.setCodiceCliente(this.codiceCliente);
		clienteDTO.setDescrizione(this.descrizione);
		clienteDTO.setKey(this.key);
		
		return clienteDTO;
		
	}
	
}
