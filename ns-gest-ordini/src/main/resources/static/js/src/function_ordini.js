const apriModalOrdine = document.getElementById('modNuovoOrdine') //pulsante chje ape la modale d'inserimento ordine

cercaOrdini();

function cercaOrdini() {


    fetch("http://localhost:8080/api/ordini/all",
            {
                method: "GET"               
            }).then(response => {
        console.log(response.status)
        return response.json()
    }).then(jsonData => {
        console.log(jsonData)      
        caricaOrdini(jsonData)
    })

}

//recupera i dati dei clienti
function cercaClienti() {

    fetch("http://localhost:8080/api/clienti/all",
            {
                method: "GET"               
            }).then(response => {
        
        return response.json()
    }).then(jsonData => {
        
        //funziona che valorizza la select del form con l'elenco dei codici clienti
        selectElementClienti('cliente',jsonData)
    })

}


//funziona che carica la tabella con l'elenco dei ordini
function caricaOrdini(jsonData) {

	var root = document.getElementById('rootTH');
	
	
	jsonData.forEach(elemento => root.insertAdjacentHTML('beforebegin', `<tr id = 'rowClienti'>
	<td>${elemento.key}</td>
	<td>${elemento.descrizione}</td>
	<td>${elemento.valore}</td>
	<td>${elemento.ore}</td>
	<td>${elemento.oreResidue}</td>
	<td>${elemento.valResiduo}</td>
	<td>${elemento.dataOrd}</td>
	<td>${elemento.cliente}</td>
	<td><button onclick = cancCliente(${elemento.key}) class="btn btn-danger">Elimina</button></td>
	
	</tr>`));
}


// cancella un cliente

function cancCliente(key){
	
	console.log(key);
	
	    fetch("http://localhost:8080/api/clienti/del/" + key,
            {method: "DELETE"
            }).then(response => {
        if (response.status == 200) {
            console.log(response.status)
			location.reload();
        }else{
			alert('Probelma nella cancellazione')
		}
    })
	
}



//funzione che precarica i valori delle select clienti del form
function selectElementClienti(id, jsonData) {    
    let element = document.getElementById(id);
    
    jsonData.forEach(cliente => {
        let opt = document.createElement('option');
        opt.value = cliente.codiceCliente;
        opt.textContent += cliente.descrizione // or opt.innerHTML += user.name
        element.appendChild(opt);
      });       
}


//quando apro la modale carico la lista dei clienti nella select di selezione
apriModalOrdine.onclick = function () {
	
	 cercaClienti();
	
};



