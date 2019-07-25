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

public class CinemaWrapper {


	public static Collection<Bean> Wrapper(String arg) throws IOException {

		// Make a URL to the web page
		URL url = new URL("http://trovacinema.repubblica.it/cinema/");

		// Get the input stream through URL Connection
		URLConnection con = url.openConnection();
		InputStream is =con.getInputStream();

		// Once you have the Input Stream, it's just plain old Java IO stuff.

		// For this case, since you are interested in getting plain-text web page
		// I'll use a reader and output the text content to System.out.

		// For binary content, it's better to directly read the bytes from stream and write
		// to the target file.


		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String lin = null;
		String line = null;
		String code="none";
		if(arg!=null) {
			code = arg.toLowerCase();
			if(code.contains(" ")) {
				code =	code.replace(" ", "-");
			}



			Collection<Bean> musei = new LinkedList<Bean>();		
			Collection<FilmBean> film = new LinkedList<FilmBean>();
			String places ="";
			boolean bool = false;
			boolean boole = false;
			Collection<String> placelist = new LinkedList<String>();

			// read each line and write to System.out
			while ((lin = br.readLine()) != null) {

				if(lin.contains("<div class=\"mm-menu-colonnina mm-left\">Cerca un cinema nella tua provincia</div>") && bool != true) {
					places = lin;
					bool = true;
					int in1=162;
					int in2=240;
					//System.out.println(places);
					while(in1 != 29102 && in1!=0 && in1!=-1) {
						placelist.add(places.substring(in1+69, in2));
						in1= places.indexOf("<a href=\"http://trovacinema.repubblica.it/programmazione-cinema/citta/",in1+1);
						in2 = places.indexOf("film/\">",in2+1);


						//System.out.println(in1); 
						//  System.out.println(placelist); 
					}
				}}

			Iterator<?> it = placelist.iterator();
			while (it.hasNext() ) {
				String luogo = (String) it.next();
				if(luogo.contains(code) && !code.equals("none")) {
					//System.out.println("ok");

					URL url2 = new URL("http://trovacinema.repubblica.it/programmazione-cinema/citta"+luogo+"film/");
					URLConnection con2 = url2.openConnection();
					InputStream is2 =con2.getInputStream();
					BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
					while ((line = br2.readLine()) != null) {
						// System.out.println(line); 

						if(line.contains("<div class=\"mm-menu-colonnina mm-left\">Cinema della citt&agrave;") && boole != true) {
							places = line;
							boole = true;
							int in1=180;
							int in2=300;
							int end = places.indexOf("Tutti i film in citt&agrave; e provincia di <strong>");
							//  System.out.println(places);
							while(in1 < end-550) {
								in1= places.indexOf("<div class=\"mm-left mm-col xs-8\">",in1+1);
								in2 = places.indexOf("</div><div class=\"stonda3",in2+1);
								//     System.out.println(in2); 
								Bean bean = new Bean();
								//		 System.out.println(line.substring(in1+33,in2));
								bean.setNome(line.substring(in1+33,in2));
								bean.setComune(code);
								bean.setProvincia(code);
								musei.add(bean);
								//System.out.println(musei); 
							}
						}

					}} 
			} return musei;
		} else return null;}


}