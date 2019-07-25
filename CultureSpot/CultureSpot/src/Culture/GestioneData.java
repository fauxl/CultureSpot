package Culture;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
		TimerScript script = new TimerScript();
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/culturespot","root","");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  

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
			request.removeAttribute("recensione");
					
			request.setAttribute("musei", model.doRetrieveByKeyLuoghiMM(posto,"Museo"));
			request.setAttribute("monumenti", model.doRetrieveByKeyLuoghiMM(posto,"Monumento"));			
			
		//	request.setAttribute("film", script.joinFilmRecensione(con,script.joinCinemaFilm(posto, con)));
			request.setAttribute("film", script.selectFilmDalDb(con));

			request.setAttribute("concerti", script.selectConcertiDalDb(posto, con));
			request.setAttribute("teatri", script.joinTeatriSoloConSpettacoli(posto, con));	
			request.setAttribute("librerie", script.selectPostiDalDb("libreria", posto, con));
			request.setAttribute("cinema", script.joinCinemaSoloConFilm(posto, con));
			request.setAttribute("spettacoli", script.joinTeatriSpettacoli(posto, con));
			
			
			request.setAttribute("postoOut", posto); 


		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
