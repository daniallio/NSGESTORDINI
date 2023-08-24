console.log('QUA')
cercaClienti();

function cercaClienti() {



    let container = document.getElementById("all");
    let container2 = document.getElementById("allDw");
    let container3 = document.getElementById("allEl");

    fetch("http://localhost:8080/api/clienti/all",
            {
                method: "GET"               
            }).then(response => {
        console.log(response.status)
        return response.json()
    }).then(jsonData => {
        console.log(jsonData)      
        caricaDocUtente(jsonData, container, container2, container3)
    })

}




//funzione che carica la lista dei documenti, la uso per fare il cerca e per caricare gli utenti condivisi
function caricaDocUtente(jsonData, container, container2, container3) {
  /*for (var i = 0; i < jsonData.length; i++) {
      //creo i link per il download e gli elementi della lista
      let el = document.createElement(`p`)
      let btnEl = document.createElement("a")
      let btnDown = document.createElement("a")
      let liDown = document.createElement("li")
      let liEl = document.createElement("li")
      btnEl.classList.add("badge-light")
      liDown.classList.add("fas")
      liDown.classList.add("fa-download")
      liEl.classList.add("fas")
      liEl.classList.add("fa-trash")
      liDown.setAttribute("style", "color:grey")
      liEl.setAttribute("style", "color:grey")
      btnDown.classList.add("badge-light")
      btnDown.setAttribute("href", "http://localhost:8080/esame_cloud/rest/documenti/download/" + jsonData[i].key);
      btnEl.setAttribute("href", "#");
      btnDown.setAttribute("style", "height:49px")
      btnEl.setAttribute("style", "height:49px")
      btnEl.setAttribute(`onclick`, `elimina(${jsonData[i].idDocumento})`)
      btnDown.style.fontSize = "200px";

      el.classList.add("list-group-item");
      el.setAttribute("id", "pDoc");
      el.innerHTML = "<b> Codice Client </b> - " + jsonData[i].codiceCliente + " - <b> Descrizione </b>" + jsonData[i].descrizione

      btnDown.appendChild(liDown)
      btnEl.appendChild(liEl)
      container.appendChild(el)
      container2.appendChild(btnDown)
      container3.appendChild(btnEl)
  }*/
	var root = document.getElementById('rootTH');
	jsonData.forEach(elemento => root.insertAdjacentHTML('beforebegin', `<tr><td>${elemento.codiceCliente}</td>
	<td>${elemento.descrizione}</td>
	<td><button onclick = cancCliente(${elemento.key}) class="btn btn-danger">Elimina</button></td></tr>`));
}


//cancella un cliente

function cancCliente(key){
	
	console.log(key);
	
	    fetch("http://localhost:8080/api/clienti/del/" + key,
            {method: "DELETE"
            }).then(response => {
        if (response.status == 200) {
            console.log(response.status)
			cercaClienti()
        }else{
			alert('Probelma nella cancellazione')
		}
    })
	
}






