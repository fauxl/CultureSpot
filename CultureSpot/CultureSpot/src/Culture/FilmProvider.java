package Culture;

import java.io.IOException;
import java.sql.SQLException;

import com.gargoylesoftware.htmlunit.WebClient;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class OrdersControl
 */
public class FilmProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// OrdersModelDS usa il DataSource
	// OrdersModelDM usa il DriverManager	
	static boolean isDataSource = true;

	static GestioneDataModel model;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			if (action.equalsIgnoreCase("read")) {
		String posto = request.getParameter("posto");
		String cinema = request.getParameter("nomecinema");
		request.removeAttribute("film");
		request.setAttribute("film", FilmWrapper.Wrapper(posto,cinema));
			}
		if (action.equalsIgnoreCase("teatro")) {
			String posto = request.getParameter("posto");
			String cinema = request.getParameter("nomecinema");
		request.removeAttribute("spettacolo");
		request.setAttribute("spettacoli", SpectacleWrapper.Wrapper(posto,cinema));
		}
			}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
