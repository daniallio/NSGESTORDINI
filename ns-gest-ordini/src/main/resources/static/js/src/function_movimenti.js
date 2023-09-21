
cercaClienti();
cercaOrdini();
const fIns= document.getElementById("insMov") //from di inserimento movimento

console.log(fIns)

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




/*fIns.addEventListener("submit", event=>{
   console.log("ENTRO")
    var form = document.getElementById('insMov');
    var data = new FormData(form)
  
    event.preventDefault()
 
    var object = {};
    data.forEach((value, key) => object[key] = value);
    var json = JSON.stringify(object);
    
    console.log(json);
    
    
   fetch("http://localhost:8080/api/movimenti/ins",
    {
        method: 'POST',
        headers: {
        	 'Accept': 'application/json',
             'Content-Type': 'application/json'
                },
        body: data
    }).then(response =>{
        console.log(response.status)
    })
    
    
    
})
*/










