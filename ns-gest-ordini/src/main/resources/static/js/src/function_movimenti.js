
cercaClienti();
cercaOrdini();
const fIns= document.getElementById("insMov") //from di inserimento movimento


cercaMovimenti()
fIns.addEventListener('submit', callbackFunction);


//recupera i dati dei clienti
function cercaClienti() {

    fetch("http://localhost:8080/api/clienti/all",
            {
                method: "GET"               
            }).then(response => {
        console.log(response.status)
        return response.json()
    }).then(jsonData => {
        console.log(jsonData)
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
        console.log(jsonData)      
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

	var root = document.getElementById('rootTH');
	//root.innerHTML = "";
	
	jsonData.forEach(elemento => root.insertAdjacentHTML('beforebegin', `<tr id = ${elemento.key}>
	<td>${elemento.cliente}</td>
	<td>${elemento.ordine}</td>
	<td>${elemento.mese}</td>
	<td>${elemento.ore}</td>
	<td>${elemento.note}</td>	
	<td><button onclick = ModMovimento(${elemento.key}) class="btn btn-danger">Modifica</button></td>	
	<td><button onclick = cancMovimento(${elemento.key}) class="btn btn-danger">Elimina</button></td>
	
	</tr>`));
}







