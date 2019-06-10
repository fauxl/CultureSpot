package Culture;

import java.sql.SQLException;
import java.util.Collection;


public interface  GestioneDataModel {

	public Collection<Bean> InsertPlace(Collection<Bean> musei) throws SQLException;
	public Collection<Bean> doRetrieveByKeyPlaces(String code, String type) throws SQLException;
	public Collection<Bean> doRetrieveByKeyLuoghiMM(String code, String type) throws SQLException;

	
}
