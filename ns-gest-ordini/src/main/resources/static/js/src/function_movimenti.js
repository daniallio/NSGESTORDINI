console.log('QUA')
cercaClienti();

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
        selectElement('cliente',jsonData)
    })

}


//funzione che precarica i valori delle select del form
function selectElement(id, jsonData) {    
    let element = document.getElementById(id);
    df = document.createDocumentFragment();
    
    console.log(element);
    //jsonData.forEach(cliente =>  element.value = cliente.codiceCliente)
    //jsonData.forEach(cliente =>  console.log(cliente.codiceCliente))
    
   for (var i = 1; i <= jsonData.lenght; i++) { // loop, i like 42.
        var option = document.createElement('option'); // create the option element
        option.value = jsonData[i].cocodiceCliente; // set the value property
        option.appendChild(document.createTextNode("option #" + i)); // set the textContent in a safe way.
        df.appendChild(option); // append the option to the document fragment
    }
   element.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
}












