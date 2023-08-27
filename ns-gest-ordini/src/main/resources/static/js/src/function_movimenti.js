console.log('QUA')
cercaClienti();
cercaOrdini();
const fIns= document.getElementById("insMov") //from di inserimento movimento




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








fIns.addEventListener("submit", event=>{
   console.log("ENTRO")
    var data = new FormData()
  
    event.preventDefault()
    
    console.log(data);
    
  /*  fetch("http://localhost:8080/esame_cloud/rest/documenti/upload",
    {
        method: 'POST',
        headers: {
                    'Content-Type': 'multipart/form-data'
                },
        body: data
    }).then(response =>{
        console.log(response.status)
    })*/
    
    
    
})











