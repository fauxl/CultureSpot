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
	public synchronized Collection<Bean> doRetrieveByKeyPlaces(String code, String place) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Bean> luoghi = new LinkedList<Bean>();
		String selectSQL = "SELECT * FROM " + GestioneDataModelDS.TABLE_NAME2 + " WHERE Provincia = ?  and Tipo=? or Località =? and Tipo=?" ;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, place);
			preparedStatement.setString(3, code);
			preparedStatement.setString(4, place);
			ResultSet rs = preparedStatement.executeQuery();
		

			while (rs.next()) {
				Bean bean = new Bean();
 
				bean.setProvincia(rs.getString("Provincia"));
				bean.setNome(rs.getString("Nome"));
				bean.setAddress(rs.getString("Località"));
				bean.setType(rs.getString("Tipo"));
				luoghi.add(bean);
				
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
		return luoghi;
	}
		
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
		
		public synchronized Collection<Bean> InsertPlace(Collection<Bean> place) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO `posti`(`Nome`, `Provincia`, `Località`, `Tipo`) "
					+ "VALUES (?,?,?,?)" ;

			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false);
				
				if (place != null && place.size() != 0) {
					Iterator<?> it = place.iterator();
					while (it.hasNext()) {
				Bean bean = (Bean) it.next();
			
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, bean.getNome());
				preparedStatement.setString(2, bean.getProvincia());
				preparedStatement.setString(3, bean.getAddress());				
				preparedStatement.setString(4, bean.getType());
				preparedStatement.executeUpdate();
				System.out.println("ok"); 

					}			
					}			//preparedStatement.executeBatch();
					connection.commit();	

				System.out.println("update effettuati");}
			
			
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
			return place;

	}
		
		
		
		

}