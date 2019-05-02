package Culture;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Culture.GestioneDataModel;
import Culture.GestioneDataModelDS;



/**
 * Servlet implementation class OrdersControl
 */
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

		
		try {

				String posto = request.getParameter("searchbar");
				
				request.removeAttribute("musei");
				request.removeAttribute("monumento");
				request.removeAttribute("librerie");
				request.removeAttribute("cinema");
				request.removeAttribute("concerti");
				request.removeAttribute("teatri");
				
				request.setAttribute("musei", model.doRetrieveByKeyMuseo(posto));
				request.setAttribute("librerie", LibraryWrapper.Wrapper(posto));
				request.setAttribute("cinema", CinemaWrapper.Wrapper(posto));
				request.setAttribute("monumenti", model.doRetrieveByKeyMonumento(posto));
				request.setAttribute("concerti", ConcertWrapper.Wrapper(posto));
				request.setAttribute("teatri", TheaterWrapper.Wrapper(posto));



			
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
