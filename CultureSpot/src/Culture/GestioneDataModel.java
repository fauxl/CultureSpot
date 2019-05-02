package Culture;

import java.sql.SQLException;
import java.util.Collection;


public interface  GestioneDataModel {

	public Collection<Bean> doRetrieveByKeyMuseo(String code) throws SQLException;
	public Collection<Bean> doRetrieveByKeyMonumento(String code) throws SQLException;

}
