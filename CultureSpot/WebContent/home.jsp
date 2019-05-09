<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> musei = (Collection<?>) request.getAttribute("musei");
	Collection<?> monumenti = (Collection<?>) request.getAttribute("monumenti");
	Collection<?> librerie = (Collection<?>) request.getAttribute("librerie");
	Collection<?> cinema = (Collection<?>) request.getAttribute("cinema");
	Collection<?> film = (Collection<?>) request.getAttribute("film");
	Collection<?> spettacoli = (Collection<?>) request.getAttribute("spettacoli");
	Collection<?> concerti = (Collection<?>) request.getAttribute("concerti");
	Collection<?> teatri = (Collection<?>) request.getAttribute("teatri");
	String postoIn = (String) request.getAttribute("postoOut");
	if (postoIn == null) {
		postoIn = "";
	}
	boolean bool = true;
%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Culture.*"%>

<html>

<script>
function chiudi(){    
    for (i = 0; i < modal.length; i++) {
		    modal[i].style.display = "none";
		  		}	
	for (i = 0; i < modal2.length; i++) {
		    modal2[i].style.display = "none";
		  		}	
	for (i = 0; i < modal3.length; i++) {
		    modal3[i].style.display = "none";
		  		}	
	for (i = 0; i < modal4.length; i++) {
		    modal4[i].style.display = "none";
		  		}
}

</script>
<script type="text/javascript">
	function filtra(element) {

		if (element.value == "Musei") {
			x = document.getElementById("contenitoreMusei");
			if (x.style.display == "none") {
				x.style.display = "block";
			} else if (x.style.display == "block") {
				x.style.display = "none";
			}
		}

		else if (element.value == "Monumenti") {
			x = document.getElementById("contenitoreMonumenti");
			if (x.style.display == "none") {
				x.style.display = "block";
			} else if (x.style.display == "block") {
				x.style.display = "none";
			}
		}

		else if (element.value == "Librerie") {
			x = document.getElementById("contenitoreLibrerie");
			if (x.style.display == "none") {
				x.style.display = "block";
			} else if (x.style.display == "block") {
				x.style.display = "none";
			}
		}

		else if (element.value == "Cinema") {
			x = document.getElementById("contenitoreCinema");
			if (x.style.display == "none") {
				x.style.display = "block";
			} else if (x.style.display == "block") {
				x.style.display = "none";
			}
		}

		else if (element.value == "Concerti") {
			x = document.getElementById("contenitoreConcerti");
			if (x.style.display == "none") {
				x.style.display = "block";
			} else if (x.style.display == "block") {
				x.style.display = "none";
			}

		}

		else if (element.value == "Teatri") {
			x = document.getElementById("contenitoreTeatri");
			if (x.style.display == "none") {
				x.style.display = "block";
			} else if (x.style.display == "block") {
				x.style.display = "none";
			}
		}
	}
</script>

<script>
	function myFunction() {
		var x = document.getElementById("risultati");
		var y = document.getElementById("mydrop");

		if (x.className == "results") {
			x.className += " responsive";
		} else {
			x.className = "results";
		}

		if (y.className == "dropdown") {
			y.className += " responsive";
		} else {
			y.className = "dropdown";
		}
	}
</script>

<script>
	function myFunction3() {
		var x = document.getElementById("risultati");
		var y = document.getElementById("mydrop3");

		if (x.className == "results") {
			x.className += " responsive";
		} else {
			x.className = "results";
		}

		if (y.className == "dropdown3") {
			y.className += " responsive";
		} else {
			y.className = "dropdown3";
		}
	}
</script>

<script>
	function myFunction2() {
		var x = document.getElementById("risultati");
		var y = document.getElementById("mydrop2");

		if (x.className == "results") {
			x.className += " responsive";
		} else {
			x.className = "results";
		}

		if (y.className == "dropdown2") {
			y.className += " responsive";
		} else {
			y.className = "dropdown2";
		}
	}
</script>

<script>
	function myFunction4() {
		var x = document.getElementById("risultati");
		var y = document.getElementById("mydrop4");

		if (x.className == "results") {
			x.className += " responsive";
		} else {
			x.className = "results";
		}

		if (y.className == "dropdown4") {
			y.className += " responsive";
		} else {
			y.className = "dropdown4";
		}
	}
</script>

<script>
	function myFunction5() {
		var x = document.getElementById("risultati");
		var y = document.getElementById("mydrop5");

		if (x.className == "results") {
			x.className += " responsive";
		} else {
			x.className = "results";
		}

		if (y.className == "dropdown5") {
			y.className += " responsive";
		} else {
			y.className = "dropdown5";
		}
	}
</script>

<script>
	function myFunction6() {
		var x = document.getElementById("risultati");
		var y = document.getElementById("mydrop6");

		if (x.className == "results") {
			x.className += " responsive";
		} else {
			x.className = "results";
		}

		if (y.className == "dropdown6") {
			y.className += " responsive";
		} else {
			y.className = "dropdown6";
		}
	}
</script>

<head>

<title>Culture Spot</title>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='stile.css' />
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">


</head>

<body>

	<div id="topBar">
		<div id="containerLogo">
			<a href="http://localhost:8080/CultureSpot/home"><img id="logo"
				src="Immagini/logo.png"></a>
		</div>
		<div id="description">Cerca tutti i Culture Spot del luogo di
			cui sei interessato</div>

	</div>
	<div id="corpo">

		<div id="searchBoxContainer">
			<!--
			<i class="fas fa-search" id="iconCerca"></i>
			-->
			<form action="home" method="post">
				<input required name="searchbar" id="searchBox" type="search"
					placeholder="Inserisci il nome della città...">
			</form>
		</div>

		<div id="checkContainer">
			<div id="suggest">Perfeziona la ricerca...</div>
			<br> <input id="check2" type="checkbox" name="Musei"
				value="Musei" onclick="filtra(this)" checked> <label>
				Musei </label> <input id="check" type="checkbox" name="Monumenti"
				value="Monumenti" onclick="filtra(this)" checked> <label>
				Monumenti </label> <input id="check" type="checkbox" name="Cinema"
				value="Cinema" onclick="filtra(this)" checked> <label>
				Cinema </label> <input id="check" type="checkbox" name="Teatri"
				value="Teatri" onclick="filtra(this)" checked> <label>
				Teatri </label> <input id="check" type="checkbox" name="Concerti"
				value="Concerti" onclick="filtra(this)" checked> <label>
				Concerti </label> <input id="check" type="checkbox" name="Librerie"
				value="Librerie" onclick="filtra(this)" checked><label>
				Librerie </label>
		</div>

	</div>


	<div id="risultati" class="results">

		<div id="contenitoreMusei" style="display: block">
			<p id="tipologiaMusei">
				<%
					if (musei != null) {
				%>
				<i class="fas fa-landmark" id="iconaMusei"></i> Trovati
				<%=musei.size()%>

				Musei a
				<%=postoIn%>
				<%
					}
				%>
				<a href="javascript:void(0);" onclick="myFunction()"><i
					class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
			</p>

			<div class="dropdown" id="mydrop">

				<%
					int y = 0;
					if (musei != null && musei.size() != 0) {
						Iterator<?> it = musei.iterator();
						while (it.hasNext()) {
							Bean bean = (Bean) it.next();
				%>

				<div id="row">
					<i class="fas fa-landmark" id="iconaMusei"></i><%=bean.getNome()%>
					<div onclick="mine2(<%=y%>)" id="programButton">

						<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
					</div>
					<div id="myModal2" class="modal">
						<div id="mappa">
							<div id="barraSuperiore">
								<span id="close" class="close" onclick="chiudi()"> <i
									class="fas fa-times" style="margin: auto;"></i></span>Mappa:
								<%=bean.getNome()%></div>

							<iframe
								src="http://localhost:8080/CultureSpot/map.jsp?lati=<%=bean.getLati()%>&longi=<%=bean.getLongi()%>"></iframe>

						</div>
					</div>

				</div>

				<br>
				<%
					y++;
						}
					}
				%>
			</div>
		</div>

		<div id="contenitoreMonumenti" style="display: block">
			<p id="tipologiaMonumenti">
				<%
					if (monumenti != null) {
				%>
				<i class="fas fa-monument" id="iconaMonumenti"></i> Trovati
				<%=monumenti.size()%>

				Monumenti a
				<%=postoIn%>
				<%
					}
				%>
				<a href="javascript:void(0);" onclick="myFunction2()"><i
					class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
			</p>
			<div class="dropdown2" id="mydrop2">

				<%
					int m = 0;
					if (monumenti != null && monumenti.size() != 0) {
						Iterator<?> it = monumenti.iterator();
						while (it.hasNext()) {
							Bean bean = (Bean) it.next();
				%>

				<div id="row">
					<i class="fas fa-monument" id="iconaMonumenti"></i><%=bean.getNome()%>
					<div onclick="mine3(<%=m%>)" id="programButton">

						<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
					</div>
					<div id="myModal3" class="modal">
						<div id="mappa">
							<div id="barraSuperiore">
								<span id="close" class="close" onclick="chiudi()"><i
									class="fas fa-times" style="margin: auto;"></i></span>Mappa:
								<%=bean.getNome()%></div>

							<iframe
								src="http://localhost:8080/CultureSpot/map.jsp?lati=<%=bean.getLati()%>&longi=<%=bean.getLongi()%>"></iframe>

						</div>
					</div>

				</div>

				<br>
				<%
					m++;
						}
					}
				%>
			</div>
		</div>

		<div id="contenitoreLibrerie" style="display: block">
			<p id="tipologiaLibrerie">
				<%
					if (librerie != null) {
				%>
				<i class="fas fa-book-reader" id="iconaLibrerie"></i>Trovate
				<%=librerie.size()%>

				Librerie a
				<%=postoIn%>
				<%
					}
				%>
				<a href="javascript:void(0);" onclick="myFunction3()"><i
					class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
			</p>
			<div class="dropdown3" id="mydrop3">

				<%
					if (librerie != null && librerie.size() != 0) {
						Iterator<?> it = librerie.iterator();
						while (it.hasNext()) {
							Bean bean = (Bean) it.next();
				%>
				<div id="row">
					<i class="fas fa-book-reader" id="iconaLibrerie"></i><%=bean.getNome()%>
					<div id="mapButton">
						<a
							href="map.jsp?lati=<%=bean.getLati()%>&longi=<%=bean.getLongi()%>">

							<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
						</a>
					</div>
				</div>
				<br>
				<%
					}
					}
				%>
			</div>
		</div>

		<div id="contenitoreCinema" style="display: block">
			<p id="tipologiaCinema">
				<%
					if (cinema != null && cinema.size() != 0) {
				%>
				<i class="fas fa-video" id="iconaCinema"></i> Trovati
				<%=cinema.size()%>

				Cinema a
				<%=postoIn%>
				<%
					} else {
				%>

				<i class="fas fa-video" id="iconaCinema"></i> Trovati 0 Cinema a
				<%=postoIn%>
				<%
					}
				%>


				<a href="javascript:void(0);" onclick="myFunction4()"><i
					class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
			</p>

			<div class="dropdown4" id="mydrop4">

				<%
					int i = 0;
					if (cinema != null && cinema.size() != 0) {
						Iterator<?> it = cinema.iterator();
						while (it.hasNext()) {
							Bean bean = (Bean) it.next();
				%>
				<div id="row">
					<i class="fas fa-video" id="iconaCinema"></i><%=bean.getNome()%>
					<div id="mapButton">
						<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
					</div>

					<div onclick="mine(<%=i%>)" id="programButton">
						<i class="fas fa-stream" id="iconaFilm"></i> Film
					</div>

					<div id="myModal" class="modal">
						<div id="dettagli">
							<div id="barraSuperiore">
								<span id="close" class="close" onclick="chiudi()"><i
									class="fas fa-times" style="margin: auto;"></i></span>Spettacoli al
								cinema
								<%=bean.getNome()%></div>
							<%
								if (film != null && film.size() != 0) {
											Iterator<?> ito = film.iterator();
											while (ito.hasNext()) {
												FilmBean films = (FilmBean) ito.next();
												if (films.getNomecinema().equals(bean.getNome())) {
							%>

							<img id="img" src="<%=films.getLocandina()%>">
							<div id="dettagliFilm">
								<span id="inGrassetto">Film: </span>
								<%=films.getFilm()%>
								<br> <span id="inGrassetto">Anno: </span>
								<%=films.getAnno()%>
								<br> <span id="inGrassetto">Genere: </span>
								<%=films.getGenere()%>
								<br> <span id="inGrassetto">Durata: </span>
								<%=films.getDurata()%>
								<br> <span id="inGrassetto">Regia: </span>
								<%=films.getRegia()%>

							</div>
							<br>

							<%
								}
											}
										}
							%>

						</div>
					</div>

				</div>
				<br>
				<%
					i++;
						}
					}
				%>

			</div>


		</div>


		<div id="contenitoreConcerti" style="display: block">
			<p id="tipologiaConcerti">
				<%
					if (concerti != null) {
				%>
				<i class="fas fa-microphone-alt" id="iconaConcerti"></i> Trovati
				<%=concerti.size()%>

				Concerti a
				<%=postoIn%>
				<%
					}
				%>
				<a href="javascript:void(0);" onclick="myFunction5()"><i
					class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
			</p>
			<div class="dropdown5" id="mydrop5">

				<%
					if (concerti != null && concerti.size() != 0) {
						Iterator<?> it = concerti.iterator();
						while (it.hasNext()) {
							Bean bean = (Bean) it.next();
				%>
				<div id="row">
					<i class="fas fa-microphone-alt" id="iconaConcerti"></i>
					<%=bean.getNome()%>
					<%=bean.getData()%>,
					<%=bean.getAddress()%>
					<div id="mapButton">
						<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
					</div>
				</div>
				<br>
				<%
					}
					}
				%>
			</div>
		</div>

		<div id="contenitoreTeatri" style="display: block">
			<p id="tipologiaTeatri">
				<%
					if (teatri != null && teatri.size() != 0) {
				%>
				<i class="fas fa-theater-masks" id="iconaTeatri"></i> Trovati
				<%=teatri.size()%>
				Teatri a
				<%=postoIn%>
				<%
					} else {
				%>

				<i class="fas fa-theater-masks" id="iconaTeatri"></i> Trovati 0
				Teatri a
				<%=postoIn%>
				<%
					}
				%>

				<a href="javascript:void(0);" onclick="myFunction6()"><i
					class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
			</p>
			<div class="dropdown6" id="mydrop6">

				<%
					int t=0;
					if (teatri != null && teatri.size() != 0) {
						Iterator<?> it = teatri.iterator();
						while (it.hasNext()) {
							Bean bean = (Bean) it.next();
				%>
				<div id="row">
					<i class="fas fa-theater-masks" id="iconaTeatri"></i><%=bean.getNome()%>

					<div id="mapButton">
						<a
							href="map.jsp?lati=<%=bean.getLati()%>&longi=<%=bean.getLongi()%>">

							<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
						</a>
					</div>
					<div onclick="mine4(<%=t%>)" id="programButton">
						<i class="fas fa-stream" id="iconaFilm"></i> Film
					</div>

					<div id="myModal4" class="modal">
						<div id="dettagli">
							<div id="barraSuperiore">
								<span id="close" class="close" onclick="chiudi()"><i
									class="fas fa-times" style="margin: auto;"></i></span>Spettacoli al
								teatro
								<%=bean.getNome()%></div>
							<%
								if (spettacoli != null && spettacoli.size() != 0) {
											Iterator<?> ito = spettacoli.iterator();
											while (ito.hasNext()) {
												TheatreBean specs = (TheatreBean) ito.next();
												if (specs.getNometeatro().equals(bean.getNome())) {
							%>

							<img id="img" src="<%=specs.getLocandina()%>">
							<div id="dettagliFilm">
								<span id="inGrassetto">Film: </span>
								<%=specs.getSpettacolo()%>
								<br> <span id="inGrassetto">Data: </span>
								<%=specs.getData()%>
								<br> <span id="inGrassetto">Autore: </span>
								<%=specs.getAutore()%>
								<br> <span id="inGrassetto">Produttore: </span>
								<%=specs.getProduttore()%>
								<br> <span id="inGrassetto">Regia: </span>
								<%=specs.getRegia()%>
								<br> <span id="inGrassetto">Protagonista/i: </span>
								<%=specs.getProtagonista()%>
							</div>
							<br>

							<%
								}
											}
										}
							%>

						</div>
					</div>

				</div>
				<br>
				<%
					t++;}
					}
				%>
			</div>
		</div>
		<script>
		
// Get the modal
var modal = document.querySelectorAll("[id='myModal']");
var modal2 = document.querySelectorAll("[id='myModal2']");
var modal3 = document.querySelectorAll("[id='myModal3']");
var modal4 = document.querySelectorAll("[id='myModal4']");
var span = document.querySelectorAll("[id='close']");

// When the user clicks the button, open the modal 
function mine(x) {

  modal[x].style.display = "block";

}

function mine2(x) {
	  modal2[x].style.display = "block";
		
}

function mine3(x) {
	  modal3[x].style.display = "block";

}

function mine4(x) {
	  modal4[x].style.display = "block";

}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	for (i = 0; i < modal.length; i++) {
		if (event.target == modal[i]) {
		    modal[i].style.display = "none";
		  }		}	
	for (i = 0; i < modal2.length; i++) {
		if (event.target == modal2[i]) {
		    modal2[i].style.display = "none";
		  }		}	
	for (i = 0; i < modal3.length; i++) {
		if (event.target == modal3[i]) {
		    modal3[i].style.display = "none";
		  }		}	
	for (i = 0; i < modal4.length; i++) {
		if (event.target == modal4[i]) {
		    modal4[i].style.display = "none";
		  }		}
}
</script>

	</div>

	<footer>
		<h3 id="copyright">Copyright &#169; 2019 by Gerardo De Rosa &
			Gianluca Annunziata</h3>
	</footer>
</body>

</html>
