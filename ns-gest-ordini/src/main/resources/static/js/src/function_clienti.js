//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
cercaClienti();

function cercaClienti() {

  fetch("http://localhost:8080/api/clienti/all",
            {
                method: "GET"               
            }).then(response => {
        console.log(response.status)
        return response.json()
    }).then(jsonData => {
        console.log(jsonData)      
        caricaClienti(jsonData)
    })

}




//funzione che carica la lista dei clienti
function caricaClienti(jsonData) {

	var root = document.getElementById('rootTH');
	
	// Get the container element where the table will be inserted
    let container = document.getElementById("containerCli");
    
    // Get the table element
    let table = document.getElementById("cliList");
    
	 //Get the Tbody  element
	  let tbody = document.getElementById("listTbody");
	
	
	//creo una tabella con gli elementi ritornati
	  jsonData.forEach((item) => {
       let tr = document.createElement("tr");
       
       // Get the values of the current object in the JSON data
       //let vals = Object.values(item);
	   let td_cliente = document.createElement("td");
	   td_cliente.innerText = item.codiceCliente; // Set the value as the text of the table cell
	   td_cliente.classList.add("cliente")
       let td_desc = document.createElement("td");
	   td_desc.innerText = item.descrizione; // Set the value as the text of the table cell
	   td_desc.classList.add("ordine")
		
	   let td_del = document.createElement("td");
	   let bt_del = document.createElement("button");
	   bt_del.innerText = "Elimina";
	   bt_del.classList.add("btn-danger")
	   td_del.appendChild(bt_del);
	   bt_del.setAttribute("id", item.key);
	   bt_del.addEventListener('click', function(){
			cancCliente(item.key);
		});
       //console.log(vals)
       // Loop through the values and create table cells
       /*vals.forEach((elem) => {
		   console.log(elem)
          let td = document.createElement("td");
          td.innerText = elem; // Set the value as the text of the table cell
          tr.appendChild(td); // Append the table cell to the table row
       });*/
		tr.appendChild(td_cliente); // Append the table cell to the table row
		tr.appendChild(td_desc); // Append the table cell to the table row

		tr.appendChild(td_del);
		tbody.appendChild(tr);
       table.appendChild(tbody); // Append the table row to the table
    });
    container.appendChild(table) // Append the table to the container element
	
	
	
	
	
	//creo la datatable
	let tableMovimenti = new DataTable('#cliList');
				
	

	
	
	/*
	
	jsonData.forEach(elemento => root.insertAdjacentHTML('beforebegin', `<tr id = 'rowClienti'><td>${elemento.codiceCliente}</td>
	<td>${elemento.descrizione}</td>
	<td><button onclick = cancCliente(${elemento.key}) class="btn btn-danger">Elimina</button></td></tr>`));*/
}


//cancella un cliente

function cancCliente(key){
	
	console.log(key);
	
	    fetch("http://localhost:8080/api/clienti/del/" + key,
            {method: "DELETE"
            }).then(response => {
        if (response.status == 200) {
            
			location.reload();
        }else{
			alert('Probelma nella cancellazione')
		}
    })
	
}






