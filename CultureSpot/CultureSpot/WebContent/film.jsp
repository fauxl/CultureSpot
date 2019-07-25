
    <%
	Collection<?> film = (Collection<?>) request.getAttribute("film");
	Collection<?> spettacoli = (Collection<?>) request.getAttribute("spettacoli");
	Collection<?> recensione = (Collection<?>) request.getAttribute("recensione");
	
%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Culture.*"%>
cazzo
	<div class="dropdown" id="mydrop">

			<%
						if (film != null && film.size() != 0 ) {
						Iterator<?> it = film.iterator();
						while (it.hasNext() ) {
						FilmBean bean = (FilmBean) it.next();
						
						
					%>
			<p id="row">
				<img id="img" src= "<%=bean.getLocandina()%>" height="100"><%=bean.getFilm()%>, <%=bean.getAnno()%>, <%=bean.getGenere()%>,
			 <%=bean.getDurata()%>,  <%=bean.getRegia()%>
			 
			</p>
			<br>
			<%
						}} %>
		</div>
		
		
		<div class="dropdown" id="mydrop">

			<%
						if (spettacoli != null && spettacoli.size() != 0 ) {
						Iterator<?> it = spettacoli.iterator();
						while (it.hasNext() ) {
						TheatreBean bean = (TheatreBean) it.next();
						
					%>
			<p id="row">
				<img id="img" src= "<%=bean.getLocandina()%>" height="100"><%=bean.getSpettacolo()%>, <%=bean.getData()%>, <%=bean.getProduttore()%>,
			 <%=bean.getAutore()%>,  <%=bean.getRegia()%>,  <%=bean.getProtagonista()%>
			 
			</p>
			<br>
			<%
						}} %>
		</div>
