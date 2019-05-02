<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> musei = (Collection<?>) request.getAttribute("musei");
	Collection<?> monumenti = (Collection<?>) request.getAttribute("monumenti");
	Collection<?> librerie = (Collection<?>) request.getAttribute("librerie");
	Collection<?> cinema = (Collection<?>) request.getAttribute("cinema");
	Collection<?> concerti = (Collection<?>) request.getAttribute("concerti");
	Collection<?> teatri = (Collection<?>) request.getAttribute("teatri");

	boolean bool = true;
%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Culture.*"%>

<html>

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
			<img id="logo" src="Immagini/logo.png">
		</div>
		<div id="description">
			Su questa piattaforma potrai ricerca tutti i "Culture spot" nella
			città o <br> provincia che desideri, per iniziare basta
			compilare il form:
		</div>
	</div>
	<div id="corpo">

		<div id="searchBoxContainer">
			<i id="icon" class="fas fa-search"></i>
			<form action="home" method="post">
				<input required name="searchbar" id="searchBox" type="search"
					placeholder="Cerca... ">
			</form>
		</div>
		<br>
		<div id="checkContainer">
			<div id="suggest">Perfeziona la ricerca...</div>
			<br> <input id="check2" type="checkbox" name="Musei"
				value="Musei"> <label> Musei </label> <input id="check"
				type="checkbox" name="Monumenti" value="Monumenti"> <label>
				Monumenti </label> <input id="check" type="checkbox" name="Cinema"
				value="Cinema"> <label> Cinema </label> <input id="check"
				type="checkbox" name="Teatri" value="Teatri"> <label>
				Teatri </label> <input id="check" type="checkbox" name="Concerti"
				value="Concerti"> <label> Concerti </label> <input
				id="check" type="checkbox" name="Librerie" value="Librerie">
			<label> Librerie </label>
		</div>

	</div>


	<div id="risultati" class="results">

		<p id="tipologia" style="background: #F5F5F5;">
			<%
				if (musei != null) {
			%>
			Sono stati trovati
			<%=musei.size()%>
			<%
				}
			%>
			Musei <a href="javascript:void(0);" onclick="myFunction()"><i
				class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
		</p>

		<div class="dropdown" id="mydrop">

			<%
				if (musei != null && musei.size() != 0) {
					Iterator<?> it = musei.iterator();
					while (it.hasNext()) {
						Bean bean = (Bean) it.next();
			%>
			<div id="row">
				<i class="fas fa-landmark" id="iconaMusei"></i><%=bean.getNome()%>
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
		<p id="tipologia" style="background: #F5F5F5;">
			<%
				if (monumenti != null) {
			%>
			Sono stati trovati
			<%=monumenti.size()%>
			<%
				}
			%>
			Monumenti <a href="javascript:void(0);" onclick="myFunction2()"><i
				class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
		</p>
		<div class="dropdown2" id="mydrop2">

			<%
				if (monumenti != null && monumenti.size() != 0) {
					Iterator<?> it = monumenti.iterator();
					while (it.hasNext()) {
						Bean bean = (Bean) it.next();
			%>
			<div id="row">
				<i class="fas fa-monument" id="iconaMonumenti"></i><%=bean.getNome()%>
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

		<p id="tipologia" style="background: #F5F5F5;">
			<%
				if (librerie != null) {
			%>
			Sono stati trovate
			<%=librerie.size()%>
			<%
				}
			%>
			Librerie <a href="javascript:void(0);" onclick="myFunction3()"><i
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
				<img id="img" src="Immagini/librerie.png" height="60"><%=bean.getNome()%>
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

		<p id="tipologia" style="background: #F5F5F5;">
			<%
			if (cinema != null && cinema.size() != 0) {
		%>
			Sono stati trovati
			<%=cinema.size()%> 
		
			Cinema
			<%
				} else {
			%>

			Sono stati trovati 0 Cinema
			<%
				}
			%>
		
			
			<a href="javascript:void(0);" onclick="myFunction4()"><i
				class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
		</p>
		<div class="dropdown4" id="mydrop4">

			<%
				if (cinema != null && cinema.size() != 0) {
					Iterator<?> it = cinema.iterator();
					while (it.hasNext()) {
						Bean bean = (Bean) it.next();
			%>
			<div id="row">
				<img id="img" src="Immagini/cinema.jpg" width="60" height="60"><%=bean.getNome()%>
				<div id="mapButton">
					<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
				</div>
				<a
					href="film?action=read&posto=<%=bean.getProvincia()%>&nomecinema=<%=bean.getNome()%>">Programmazione</a>
			</div>
			<br>
			<%
				}
				}
			%>
		</div>
		<p id="tipologia" style="background: #F5F5F5;">
			<%
				if (concerti != null) {
			%>
			Sono stati trovati
			<%=concerti.size()%>
			<%
				}
			%>
			Concerti <a href="javascript:void(0);" onclick="myFunction5()"><i
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
				<img id="img" src="Immagini/concerto.png" height="50"><%=bean.getNome()%>
				<%=bean.getData()%>,
				<%=bean.getAddress()%><div id="mapButton">
					<i id="iconaMappa" class="fas fa-map-marker-alt"></i> Mappa
				</div>
			</div>
			<br>
			<%
				}
				}
			%>
		</div>

		<p id="tipologia" style="background: #F5F5F5;">

		<%
			if (teatri != null && teatri.size() != 0) {
		%>
			Sono stati trovati
			<%=teatri.size()%>
			Teatri
			<%
			} else {
		%>

			Sono stati trovati 0 Teatri
			<%
				}
			%>

			<a href="javascript:void(0);" onclick="myFunction6()"><i
				class="fas fa-arrow-circle-down" id="apriRisultati"></i> </a>
		</p>
		<div class="dropdown6" id="mydrop6">

			<%
				if (teatri != null && teatri.size() != 0) {
					Iterator<?> it = teatri.iterator();
					while (it.hasNext()) {
						Bean bean = (Bean) it.next();
			%>
			<div id="row">
				<img id="img" src="Immagini/teatro.jpg" height="50"><%=bean.getNome()%>
				<a
					href="film?action=teatro&posto=<%=bean.getProvincia()%>&nomecinema=<%=bean.getNome()%>">Programmazione</a>
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

	<footer>

		<h3 id="copyright">Copyright &#169; 2019 by Gerardo De Rosa &
			Gianluca Annunziata</h3>
	</footer>

</body>

</html>
