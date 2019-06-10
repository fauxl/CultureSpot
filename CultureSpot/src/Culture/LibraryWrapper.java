package Culture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sql.DataSource;


public class LibraryWrapper  {

	public static Collection<Bean> Wrapper(String arg) throws IOException, SQLException {

		URL url = new URL("https://www.librerie.it/librerie");

		URLConnection con = url.openConnection();
		InputStream is =con.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String lin = null;
		String line = null;
		String line2 = null;
		String code = ">"+arg+"<";
		Collection<Bean> musei = new LinkedList<Bean>();
		 GestioneDataModel model = new GestioneDataModelDS();
		
		while ((lin = br.readLine()) != null) {

			if(lin.toLowerCase().contains(code.toLowerCase())){

				int index1 = lin.lastIndexOf("href") + 6;
				int index2= lin.lastIndexOf("\" ");
				int m=1;


				//System.out.println(index2);
				//System.out.println("https://www.librerie.it" + line.substring(index1,index2));
				for(int j=0;j<m;j++) {
					URL url2 = new URL("https://www.librerie.it" + lin.substring(index1,index2)+"?page="+j);
					//System.out.println(url2);
					URLConnection con2 = url2.openConnection();
					InputStream is2 =con2.getInputStream();
					BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
					while ((line = br2.readLine()) != null) {

						Bean bean = new Bean();
						int in3=-1;
						in3 = line.lastIndexOf("title=\"Vai all&#039;ultima pagina\"");

						if(in3!=-1) {
							if(line.substring(in3-4,in3-2).contains("=")){
								m = Integer.parseInt(line.substring(in3-3,in3-2)) +1;
							} else
								m = Integer.parseInt(line.substring(in3-4,in3-2)) +1;

						}


						int in1 = line.lastIndexOf("\">") + 2;
						int in2= line.lastIndexOf("</a>          </td>"); 
						if(in1!=1 && in2!=-1) {
							boolean yes = false;

							bean.setNome(line.substring(in1,in2));
							bean.setProvincia(arg);

							while ((line2 = br2.readLine()) != null && yes == false) {

								int in4= line2.lastIndexOf("          </td>"); 
								if (in4!=-1 && in4<40 && in4!=16) {
									yes = true;

									bean.setAddress(line2.substring(11,in4));	
									bean.setType("Libreria");

//									System.out.println(map.getCoordinates((line2.substring(11,in4)+" "+arg).toString()));

									musei.add(bean);
									

									//     System.out.println(bean.toString());
								}}



						}
					}


				} 

			}

		}

		return model.InsertPlace(musei) ;
		
	}
}