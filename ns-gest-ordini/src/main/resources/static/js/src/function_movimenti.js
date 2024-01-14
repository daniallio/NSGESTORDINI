/*var options = {
  valueNames: [ 'cliente', 'ordine', 'mese', 'ore','note']
};

var userList = new List('movimenti', options);
*/

cercaClienti();
cercaOrdini();
const fIns= document.getElementById("insMov") //form di inserimento movimento



   $(document).ready(function() {

	   cercaMovimenti()
    });



fIns.addEventListener('submit', callbackFunction);


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




function cercaOrdini() {
   

    fetch("http://localhost:8080/api/ordini/all",
            {
                method: "GET"               
            }).then(response => {
        console.log(response.status)
        return response.json()
    }).then(jsonData => {
            
        selectElementOrdini('ordine',jsonData)
    })

}




//funzione che precarica i valori delle select clienti del form
function selectElementOrdini(id, jsonData) {    
    let element = document.getElementById(id);
    
    jsonData.forEach(ordine => {
        let opt = document.createElement('option');
        opt.value = ordine.key;
        opt.textContent += ordine.descrizione // or opt.innerHTML += user.name
        element.appendChild(opt);
      });
    
    
    
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


//funzione per inseire i movimenti
function callbackFunction(event) {
    event.preventDefault();
    const myFormData = new FormData(event.target);
    
    var formDataObj = {};
    //costruisco un oggetto con i valori inseriti nel form
    formDataObj = Object.fromEntries(myFormData.entries());  	
    //lo converto in JSON
    var formDataObjToJSON = JSON.stringify(formDataObj);
   fetch("http://localhost:8080/api/movimenti/ins",
    {
        method: 'POST',
        headers: {
        	 'Accept': 'application/json',
             'Content-Type': 'application/json'
                },
        body: formDataObjToJSON
    }).then(response =>{
    	
    	console.log(response.status)
    	
    	if(response.status==200){    
    		
            let esito = document.getElementById("esitoMov")
            esito.classList.add("bg-success");
            esito.innerHTML = "Inserimento avvenuto con successo"            
         }
    	
         if(response.status != 200){            
            let esito = document.getElementById("esitoMov")
            esito.classList.add("alert-danger");
            esito.innerHTML = "Errore nell'inseirimento del movimento"
          }
    	
        
    })   

	
}

//carica tutti i movimenti
function cercaMovimenti() {
	

    fetch("http://localhost:8080/api/movimenti/all",
            {
                method: "GET"               
            }).then(response => {        
        return response.json()
    }).then(jsonData => {            
        caricaMovimenti(jsonData)
    })

}


//funziona che carica la tabella con l'elenco dei movimenti
function caricaMovimenti(jsonData) {

	
    // Get the container element where the table will be inserted
    let container = document.getElementById("containerMov");
    
    // Get the table element
    let table = document.getElementById("movList");
    
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
	   let td_cliente = document.createElement("td");
	   td_cliente.innerText = item.cliente; // Set the value as the text of the table cell
	   td_cliente.classList.add("cliente")
       let td_ordine = document.createElement("td");
	   td_ordine.innerText = item.ordine; // Set the value as the text of the table cell
	   td_ordine.classList.add("ordine")
		let td_mese = document.createElement("td");
	   td_mese.innerText = item.mese; // Set the value as the text of the table cell
	    td_mese.classList.add("mese")
	   let td_ore = document.createElement("td");
	   td_ore.innerText = item.ore; // Set the value as the text of the table cell
	    td_ore.classList.add("ore")
	   let td_note = document.createElement("td");
	   td_note.innerText = item.note; // Set the value as the text of the table cell
	   td_note.classList.add("note")
	   let td_modify = document.createElement("td");
	   let bt_modify = document.createElement("button");
	   bt_modify.innerText = "Modifica";
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
		tr.appendChild(td_cliente); // Append the table cell to the table row
		tr.appendChild(td_ordine); // Append the table cell to the table row
		tr.appendChild(td_mese); // Append the table cell to the table row
		tr.appendChild(td_ore); // Append the table cell to the table row
		tr.appendChild(td_note); // Append the table cell to the table row
		tr.appendChild(td_modify);
		tbody.appendChild(tr);
       table.appendChild(tbody); // Append the table row to the table
    });
    container.appendChild(table) // Append the table to the container element
	
	
		
	
	
	
	
	/*
	var root = document.getElementById('rootLmovTH');
		
	jsonData.forEach(elemento => root.insertAdjacentHTML('beforebegin', `<tr id = ${elemento.key}>
	<td class ="cliente">${elemento.cliente}</td>
	<td class ="ordine">${elemento.ordine}</td>
	<td class ="mese">${elemento.mese}</td>
	<td class ="ore">${elemento.ore}</td>
	<td class = "note"> ${elemento.note}</td>
	<td><button onclick = modMov(${elemento.key}) class="btn btn-danger">Modifica</button></td>
	
	
	</tr>`));
	*/
	
	
	/*
	
	var options = {
			  valueNames: [ 'cliente', 'ordine','mese','ore','note']
			};

	var contactList = new List('containers', options);
	*/
	
	let tableMovimenti = new DataTable('#movList');
			
	
	}
	







