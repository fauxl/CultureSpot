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
			String action = request.getParameter("action");

			if (action != null) {
				
				String posto2 = request.getParameter("posto");
				if (action.equalsIgnoreCase("read")) {
				
			String cinema = request.getParameter("nomecinema");
			request.removeAttribute("film");
			request.setAttribute("film", FilmWrapper.Wrapper(posto2,cinema));
				}
			if (action.equalsIgnoreCase("teatro")) {
				String cinema = request.getParameter("nomecinema");
			request.removeAttribute("spettacolo");
			request.setAttribute("spettacoli", SpectacleWrapper.Wrapper(posto2,cinema));
			}
			
			
				}
			request.removeAttribute("musei");
			request.removeAttribute("monumento");
			request.removeAttribute("librerie");
			request.removeAttribute("film");
			request.removeAttribute("cinema");
			request.removeAttribute("concerti");
			request.removeAttribute("teatri");
			
			Collection<?> cinema = (Collection<?>) CinemaWrapper.Wrapper(posto);
			Collection<FilmBean> film=  new LinkedList<FilmBean>();

			if (cinema != null && cinema.size() != 0) {
				Iterator<?> it = cinema.iterator();
				while (it.hasNext()) {
					Bean bean = (Bean) it.next();	
					if (film!=null) {
					film.addAll(FilmWrapper.Wrapper(posto,bean.getNome()));
					}
				}
				}
				
			request.setAttribute("film", film);
			request.setAttribute("musei", model.doRetrieveByKeyMuseo(posto));
			request.setAttribute("librerie", LibraryWrapper.Wrapper(posto));
			request.setAttribute("cinema", CinemaWrapper.Wrapper(posto));
			request.setAttribute("monumenti", model.doRetrieveByKeyMonumento(posto));
			request.setAttribute("concerti", ConcertWrapper.Wrapper(posto));
			// request.setAttribute("teatri", null); //
			request.setAttribute("teatri", TheaterWrapper.Wrapper(posto));
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
