package Culture;

import java.sql.SQLException;
import java.util.Collection;


public interface  GestioneDataModel {


	public Collection<Bean> doRetrieveByKeyLuoghiMM(String code, String type) throws SQLException;

	
}
