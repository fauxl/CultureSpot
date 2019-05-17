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

public class TheaterWrapper {

	public static  Collection<Bean> Wrapper(String arg) throws IOException  {

		String cerca = "none";
		if(arg!=null) {
			cerca = arg.toLowerCase();
			System.out.println(cerca);
			if(cerca.contains(" ")) {
				cerca =	cerca.replace(" ", "+");
			}

			
			int j=1;
			Collection<Bean> musei = new LinkedList<Bean>();		

			for(j=1;j<40;j++) {

				URL url = new URL("https://www.teatro.it/teatri/?city="+cerca+"&order_by=more_sits&page="+j);

				URLConnection con = url.openConnection();
				con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				InputStream is =con.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String lin = null;

				Collection<String> theaterlist = new LinkedList<String>();

				while ((lin = br.readLine()) != null) {

					//   System.out.println(lin);

					if(lin.contains("<h3 class=\"post-title title-large\">")) {
						theaterlist.add(lin);
					}

					if(lin.contains("<br>") && lin.length()<60 && lin.length()>10) {
						theaterlist.add(lin);

					}
				}


				Iterator<?> it = theaterlist.iterator();
				while (it.hasNext() ) {
					String teatro = (String) it.next();
					String posto = "";

					if(it.hasNext()) {
						posto = (String) it.next(); 
					}

					int in1= teatro.lastIndexOf(" title=");
					int in2 = teatro.lastIndexOf("\">");
					int in3 = posto.indexOf("<br>");
					if(in3 !=-1) {
						Bean bean = new Bean();
						//System.out.println(teatro.substring(in1+8,in2));
						bean.setNome(teatro.substring(in1+8,in2));
						bean.setAddress(posto.substring(0, in3));
						//System.out.println(map.getCoordinates((posto.substring(0, in3)).toString()));

						bean.setProvincia(cerca);
						musei.add(bean);
						//  	System.out.println(musei);
					
						if(in1== -1) {
							j=39;
						}
					}
					
				}
				
			}return musei;
		} else return null;}
}

















