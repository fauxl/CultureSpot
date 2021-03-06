package Culture;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Culture.GestioneDataModel;
import Culture.GestioneDataModelDS;


public class GestioneData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// OrdersModelDS usa il DataSource
	// OrdersModelDM usa il DriverManager	
	static boolean isDataSource = true;

	static GestioneDataModel model;

	static {
		if (isDataSource) {
			model = new GestioneDataModelDS();
		}
	}

	public GestioneData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String posto = request.getParameter("searchbar");

		try {

			request.removeAttribute("musei");
			request.removeAttribute("monumento");
			request.removeAttribute("librerie");
			request.removeAttribute("film");
			request.removeAttribute("cinema");
			request.removeAttribute("concerti");
			request.removeAttribute("film");
			request.removeAttribute("spettacoli");
			request.removeAttribute("teatri");
			
			Collection<?> cinema = (Collection<?>) model.doRetrieveByKeyPlaces(posto,"cinema");
			
			if (cinema == null || cinema.size() == 0) {
				 cinema = (Collection<?>) CinemaWrapper.Wrapper(posto);
			}
			
			Collection<?> teatro = (Collection<?>) model.doRetrieveByKeyPlaces(posto,"teatro");
			
			if (teatro == null || teatro.size() == 0) {
				 teatro = (Collection<?>) TheaterWrapper.Wrapper(posto);
			}
			
			Collection<?> libreria = (Collection<?>) model.doRetrieveByKeyPlaces(posto,"Libreria");
			
			if (libreria  == null || libreria .size() == 0) {
				libreria  = (Collection<?>) LibraryWrapper.Wrapper(posto);
			}



			Collection<FilmBean> film =  new LinkedList<FilmBean>();

			if (cinema != null && cinema.size() != 0) {
				Iterator<?> it = cinema.iterator();
				while (it.hasNext()) {
					Bean bean = (Bean) it.next();	
					if (cinema != null) {
						Collection<FilmBean> films = FilmWrapper.Wrapper(posto,bean.getNome());
						if(films !=null){
						film.addAll(films);
					}
				}
				}}
			
		/*	Collection<TheatreBean> spettacoli =  new LinkedList<TheatreBean>();

			if (teatri != null && teatri.size() != 0) {
				Iterator<?> it = teatri.iterator();
				while (it.hasNext()) {
					Bean bean = (Bean) it.next();	
					if (teatri != null) {
						Collection<TheatreBean> spectacles = SpectacleWrapper.Wrapper(posto,bean.getNome());
						if(spectacles !=null){
						spettacoli.addAll(spectacles);
					}}
				}
				}*/
				
			request.setAttribute("film", film);
			request.setAttribute("spettacoli", null);
			request.setAttribute("musei", model.doRetrieveByKeyLuoghiMM(posto,"Museo"));
			request.setAttribute("librerie", libreria);
			request.setAttribute("cinema", cinema);
			request.setAttribute("monumenti", model.doRetrieveByKeyLuoghiMM(posto,"Monumento"));
			request.setAttribute("concerti", ConcertWrapper.Wrapper(posto));
			// request.setAttribute("teatri", null); //
			request.setAttribute("teatri", teatro);
			request.setAttribute("postoOut", posto); 


		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
