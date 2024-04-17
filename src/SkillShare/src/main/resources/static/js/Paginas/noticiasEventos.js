async function requestNoticias() {

	serverRequester.fazerGet("/destaques").then((res) => {
	
		var divNoticias = document.getElementById("noticias");

		res.responseJson.map((noticia) => {
		
		console.log(noticia);
		
			var id_img_noticia = "imagemnoticia" + noticia.id.toString();
			console.log(id_img_noticia);

			let imgNoticia = document.createElement("img");
			imgNoticia.classList.add("thumbNoticia");
			imgNoticia.id = id_img_noticia;
			imgNoticia.alt = "Imagem da notícia";
			
			var divDescricaoNoticia = document.createElement("div");
			divDescricaoNoticia.id = "descricaonoticias";
			
			let p1 = document.createElement("p");
			p1.id = "margintopnoticia";
			p1.textContent = noticia.titulo;

			let p2 = document.createElement("p");
			p2.id = "marginnoticia";
			p2.textContent = noticia.sinopse;

			let p3 = document.createElement("p");
			p3.id = "leramaisdanoticia";
			p3.textContent = "Ler conteúdo completo";
			p3.onclick = function(){
				window.sessionStorage.setItem("noticia", noticia.id.toString());
				window.sessionStorage.setItem("evento", "0");

				window.location.href = "noticias";
			}
			
			divDescricaoNoticia.appendChild(p1);
			divDescricaoNoticia.appendChild(p2);
			divDescricaoNoticia.appendChild(p3);
			
			let aNoticia = document.createElement("div");
			aNoticia.id = "adecoration";

			aNoticia.appendChild(imgNoticia);
			aNoticia.appendChild(divDescricaoNoticia);
			
			divNoticias.appendChild(aNoticia);

			renderIMG(noticia.thumb.arquivo.conteudo, noticia.thumb.arquivo.tipoArquivo, id_img_noticia);
			
		})

	}).catch((err) => {
		console.log(err);
	});
}

async function requestEventos() {
	serverRequester.fazerGet("/eventos").then((res) => {

		var divEventos = document.getElementById("eventos");

		res.responseJson.map((evento) => {
		
		console.log(evento);

			var id_img_evento = "imagemevento" + evento.id.toString();
			console.log(id_img_evento);

			let imgEvento = document.createElement("img");
			imgEvento.classList.add("thumbNoticia");
			imgEvento.id = id_img_evento;
			imgEvento.alt = "Imagem do evento";
			
			var divDescricaoEvento = document.createElement("div");
			divDescricaoEvento.id = "descricaonoticias";

			let p1 = document.createElement("p");
			p1.id = "margintopnoticia";
			p1.textContent = evento.titulo;

			let p2 = document.createElement("p");
			p2.id = "marginnoticia";
			p2.textContent = evento.sinopse;

			let p3 = document.createElement("p");
			p3.id = "leramaisdanoticia";
			p3.textContent = "Ler conteúdo completo";
			p3.onclick = function(){
				window.sessionStorage.setItem("noticia", "0");
				window.sessionStorage.setItem("evento", evento.id.toString());

				window.location.href = "noticias";
			}
			
			divDescricaoEvento.appendChild(p1);
			divDescricaoEvento.appendChild(p2);
			divDescricaoEvento.appendChild(p3);
			
			let aEvento = document.createElement("a");
			aEvento.id = "adecoration";
			aEvento.href = "/evento/" + evento.id;
			
			aEvento.appendChild(imgEvento);
			aEvento.appendChild(divDescricaoEvento);
			
			divEventos.appendChild(aEvento);

			renderIMG(evento.thumb.arquivo.conteudo, evento.thumb.arquivo.tipoArquivo, id_img_evento);

		})

	}).catch((err) => {
		console.log(err);
	});
}

requestNoticias();
requestEventos();