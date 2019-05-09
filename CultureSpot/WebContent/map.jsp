<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<script src="https://unpkg.com/leaflet@1.0.1/dist/leaflet.js"></script>
<link href="https://unpkg.com/leaflet@1.0.1/dist/leaflet.css" rel="stylesheet"/>
		
</head>
<body>

<div id="osm-map">	<script>


	//Where you want to render the map.
	var element = document.getElementById('osm-map');

	// Height has to be set. You can do this in CSS too.
	element.style = 'height:420px;';

	// Create Leaflet map on map element.
	var map = L.map(element);

	// Add OSM tile leayer to the Leaflet map.
	L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
	}).addTo(map);

	// Target's GPS coordinates.
	var params = {};
	var parser = document.createElement('a');
	parser.href = window.location.href ;
	var query = parser.search.substring(1);
	var vars = query.split('&');
		var pair = vars[0].split('=');
		var pair2 = vars[1].split('=');

	var target = L.latLng(pair[1], pair2[1]);
	// Set map's center to target with zoom 14.
	map.setView(target, 14);

	// Place a marker on the same location.
	L.marker(target).addTo(map);</script></div>
	
			
</body>
</html>