package Culture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
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
	
	private static final String TABLE_NAME = "luoghimm";
	private static final String TABLE_NAME2 = "posti";
	

	
	
		
		@Override
		public synchronized Collection<Bean> doRetrieveByKeyLuoghiMM(String code, String type) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			Collection<Bean> places = new LinkedList<Bean>();
			String selectSQL2 = "SELECT * FROM " + GestioneDataModelDS.TABLE_NAME + " WHERE Provincia = ? and Tipo=? or Comune =?  and Tipo=?" ;

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1, code);
				preparedStatement.setString(2, type);
				preparedStatement.setString(3, code);
				preparedStatement.setString(4, type);
				ResultSet rs2 = preparedStatement.executeQuery();

				
				while (rs2.next()) {
					Bean bean = new Bean();
	 
					bean.setComune(rs2.getString("Comune"));
					bean.setProvincia(rs2.getString("Provincia"));
					bean.setRegione(rs2.getString("Regione"));
					bean.setNome(rs2.getString("Nome"));
					bean.setLongi(rs2.getFloat("Longitudine"));
					bean.setLati(rs2.getFloat("Latitudine"));
					places.add(bean);
					
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
			return places;
	}
		
		
		
		

}