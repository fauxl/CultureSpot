package Culture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.sql.DataSource;
import javax.sql.DataSource;

import Culture.Bean;
import Culture.GestioneDataModel;

public class GestioneDataModelDS implements GestioneDataModel{
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/culturespot");
		}
		catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "museo";
	private static final String TABLE_NAME2 = "monumento";

	
	@Override
	public synchronized Collection<Bean> doRetrieveByKeyMuseo(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Bean> musei = new LinkedList<Bean>();
		Collection<Bean> monumenti = new LinkedList<Bean>();
		String selectSQL = "SELECT * FROM " + GestioneDataModelDS.TABLE_NAME + " WHERE Provincia = ? or Comune =?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, code);
			ResultSet rs = preparedStatement.executeQuery();
		

			while (rs.next()) {
				Bean bean = new Bean();
 
				bean.setComune(rs.getString("Comune"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
				bean.setNome(rs.getString("Nome"));
				bean.setLongi(rs.getFloat("Longitudine"));
				bean.setLati(rs.getFloat("Latitudine"));
				musei.add(bean);
				
			}
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}
		return musei;
	}
		
		@Override
		public synchronized Collection<Bean> doRetrieveByKeyMonumento(String code) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			Collection<Bean> monumenti = new LinkedList<Bean>();
			String selectSQL2 = "SELECT * FROM " + GestioneDataModelDS.TABLE_NAME2 + " WHERE Provincia = ? or Comune =?  ";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1, code);
				preparedStatement.setString(2, code);
				ResultSet rs2 = preparedStatement.executeQuery();

				
				while (rs2.next()) {
					Bean bean = new Bean();
	 
					bean.setComune(rs2.getString("Comune"));
					bean.setProvincia(rs2.getString("Provincia"));
					bean.setRegione(rs2.getString("Regione"));
					bean.setNome(rs2.getString("Nome"));
					bean.setLongi(rs2.getFloat("Longitudine"));
					bean.setLati(rs2.getFloat("Latitudine"));
					monumenti.add(bean);
					
				}
			}
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				}
				finally {
					if (connection != null)
						connection.close();
				}
			}
			return monumenti;
	}

}