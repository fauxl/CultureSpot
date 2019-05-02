package Culture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ConcertWrapper {

	public static Collection<Bean> Wrapper(String arg) throws IOException {

		// Make a URL to the web page

		String cerca = "none";
		if(arg!=null) {
			cerca = arg;
			if(cerca.contains(" ")) {
				cerca =	cerca.replace(" ", "+");
			}
		} 


		URL url = new URL("https://www.rockol.it/concerti-ricerca?artista=&citta="+cerca+"&dataDD=&dataMM=&dataYYYY=&locale=&provincia=&regione=");

		URLConnection con = url.openConnection();
		InputStream is =con.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String lin = null;
		String line = null;
		String line2 = null;
		String code="none";
		if(arg!=null) {
			code = arg;
		} 

		Collection<Bean> musei = new LinkedList<Bean>();		
		String places ="";
		boolean bool = false;
		Collection<String> concertlist = new LinkedList<String>();

		while ((lin = br.readLine()) != null) {

			//System.out.println(lin);

			if(lin.contains("addthis:url=\"https://www.rockol.itDettagli evento")) {
				concertlist.add(lin);
			}

		}


		Iterator<?> it = concertlist.iterator();
		while (it.hasNext() ) {
			String concerto = (String) it.next();

			int in1= concerto.indexOf("addthis:url=\"https://www.rockol.itDettagli evento");
			int in2 = concerto.indexOf(" - ");
			int in3 = concerto.indexOf(" presso ");
			int in4= concerto.lastIndexOf(" - ");

			Bean bean = new Bean();
			//System.out.println(concerto.substring(in1+51,in2));
			bean.setData(concerto.substring(in1+50,in2));
			bean.setNome(concerto.substring(in2+4,in3-1));
			bean.setAddress(concerto.substring(in3+8, in4));
			bean.setProvincia(code);
			musei.add(bean);
			//	System.out.println(musei);

		}return musei;
	}
}

















