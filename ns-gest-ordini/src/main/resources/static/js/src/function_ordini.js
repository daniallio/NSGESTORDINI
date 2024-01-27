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


//funziona che carica la tabella con l'elenco dei movimenti
function caricaOrdini(jsonData) {

	
	
	
	  // Get the container element where the table will be inserted
    let container = document.getElementById("ordContainer");
    
    // Get the table element
    let table = document.getElementById("ordList");
    
	 //Get the Tbody  element
	  let tbody = document.getElementById("listTbody");
	 
	 
   /* // Get the keys (column names) of the first object in the JSON data
    let cols = Object.keys(jsonData[0]);
    
    // Create the header element
    let thead = document.createElement("thead");
    let tr = document.createElement("tr");*/
    
    // Loop through the column names and create header cells
    /*cols.forEach((item) => {
       let th = document.createElement("th");
       th.innerText = item; // Set the column name as the text of the header cell
       tr.appendChild(th); // Append the header cell to the header row
    });
    thead.appendChild(tr); // Append the header row to the header
    table.append(tr) // Append the header to the table*/
    
    // Loop through the JSON data and create table rows
    jsonData.forEach((item) => {
       let tr = document.createElement("tr");
       
       // Get the values of the current object in the JSON data
       //let vals = Object.values(item);
	   let td_codice = document.createElement("td");
	   td_codice.innerText = item.key; // Set the value as the text of the table cell
	   td_codice.classList.add("cliente")
	   
	   let td_descrizione = document.createElement("td");
	   td_descrizione.innerText = item.descrizione; // Set the value as the text of the table cell
	   td_descrizione.classList.add("descrizione")
	   
	   let td_valore = document.createElement("td");
	   td_valore.innerText = item.valore; // Set the value as the text of the table cell
	   td_valore.classList.add("valore")
	   
       let td_ore_ord = document.createElement("td");
	   td_ore_ord.innerText = item.ore; // Set the value as the text of the table cell
	   td_ore_ord.classList.add("oreOrd")
	   
		let td_ore_res = document.createElement("td");
	   td_ore_res.innerText = item.oreResidue; // Set the value as the text of the table cell
	   td_ore_res.classList.add("oreRes")
	   
	   let td_val_res = document.createElement("td");
	   td_val_res.innerText = item.valResiduo; // Set the value as the text of the table cell
	   td_val_res.classList.add("valRes")
	    
	   let td_data_ord = document.createElement("td");
	   td_data_ord.innerText = item.dataOrd; // Set the value as the text of the table cell
	   td_data_ord.classList.add("dataOrd")
	    
	   let td_cliente = document.createElement("td");
	   td_cliente.innerText = item.cliente; // Set the value as the text of the table cell
	   td_cliente.classList.add("cliente")
	   
	   let td_modify = document.createElement("td");
	   let bt_modify = document.createElement("button");
	   bt_modify.innerText = "Elimina";
	   //bt_modify.classList.add("btn btn-danger")
	   td_modify.appendChild(bt_modify);
	   
	   
       //console.log(vals)
       // Loop through the values and create table cells
       /*vals.forEach((elem) => {
		   console.log(elem)
          let td = document.createElement("td");
          td.innerText = elem; // Set the value as the text of the table cell
          tr.appendChild(td); // Append the table cell to the table row
       });*/
		tr.appendChild(td_codice); // Append the table cell to the table row
		tr.appendChild(td_descrizione); // Append the table cell to the table row
		tr.appendChild(td_valore); // Append the table cell to the table row
		tr.appendChild(td_ore_ord); // Append the table cell to the table row
		tr.appendChild(td_ore_res); // Append the table cell to the table row
		tr.appendChild(td_val_res); // Append the table cell to the table row
		tr.appendChild(td_data_ord); // Append the table cell to the table row
		tr.appendChild(td_cliente); // Append the table cell to the table row
		tr.appendChild(td_modify);
		tbody.appendChild(tr);
		table.appendChild(tbody); // Append the table row to the table
    });
    container.appendChild(table) // Append the table to the container element
	
	
		
	
	
    //creo la data table
	let tableMOrdini= new DataTable('#ordList');
	
	
	
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



