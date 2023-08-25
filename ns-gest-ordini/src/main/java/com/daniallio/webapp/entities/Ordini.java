package com.daniallio.webapp.entities;

import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ORDINI")
@Data

public class Ordini {

	@Id
	@Column(name = "ord_code", nullable = false, updatable = false, insertable = false)
	private String key;

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
	@EqualsAndHashCode.Exclude // per problema su loombook
	@JoinColumn(name = "ord_cli", referencedColumnName = "cli_key") // name è il nome della colonna FK, reference.. nome
																	// della chiave primaria nella tab collegata
	@JsonBackReference(value = "ordine") // punto di arrivo che non verrà mostrato se estraggo il json della classe
	private Clienti cliente;
	
	
	// relazione una molti con la tabella MOVIMENTI
	//un ordine può avere più movimenti
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference (value="movimento")// solo quando il webservice resituisce un json, punto di partenza nella classe	 colllegata invece inserisco JSONBACK
	private Set<Movimenti> movimenti = new HashSet();
	

	public OrdiniDTO ordiniToDTO() {

		OrdiniDTO ordineDTO = new OrdiniDTO();

		ordineDTO.setKey(this.key);
		ordineDTO.setDescrizione(this.descrizione);
		ordineDTO.setValore(this.valore);
		ordineDTO.setDataOrd(this.dataOrd);
		ordineDTO.setOre(this.ore);
		ordineDTO.setOreResidue(this.oreResidue);
		ordineDTO.setValResiduo(this.valResiduo);
		ordineDTO.setCliente(this.cliente.getCodiceCliente());
		
		return ordineDTO;

	}
}
