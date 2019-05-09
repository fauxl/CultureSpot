package Culture;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FilmWrapper {

	public static Collection<FilmBean> Wrapper(String arg,String ncinema) throws IOException{
		
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
				String code=arg;
				String nomecinema =ncinema;
				if(arg!=null) {
					code = arg.toLowerCase();

					}

					Collection<Bean> musei = new LinkedList<Bean>();		
					Collection<FilmBean> film = new LinkedList<FilmBean>();
					String places ="";
					boolean bool = false;
					boolean boole = false;
					Collection<String> placelist = new LinkedList<String>();
					HtmlPage page = null;
					int i=0;
					WebClient client = new WebClient();  
					int j=0;
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
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		try {  
		
		  String searchUrl = "http://trovacinema.repubblica.it/programmazione-cinema/citta"+luogo+"film/";
		 page = client.getPage(searchUrl);
		}catch(Exception e){
			  e.printStackTrace();
			  return null;
			}
						}}

			List<HtmlAnchor> posti = page.getByXPath("//div[@class='mm-menu-colonnina']/a");
			
			if(posti.isEmpty()){  
				  System.out.println("No items found !");
				}else{
					  for(HtmlAnchor cinema: posti){
						 if( cinema.getTextContent().contains(nomecinema)) {
							 try {  
									
								  String searchUrl = cinema.getHrefAttribute();
								 page = client.getPage(searchUrl);
								}catch(Exception e){  e.printStackTrace();
								}
									  
						  System.out.println(cinema.getHrefAttribute());
					  }}
				}

			List<HtmlElement> items = page.getByXPath("//div[@class='mm-padding-16 mm-col md-9']");
	if(items.isEmpty()){  
	  System.out.println("No items found !");
	  return null;
	}else{
		  System.out.println(items.size());	

		List<HtmlElement> itemss = page.getByXPath("//ul[@class='mm-ul']/li");
		if(itemss.isEmpty()){  
		  System.out.println("No items found !");
		}else{
			  System.out.println(itemss.size());	
			  List<DomAttr>  ref =  page.getByXPath("//section[@class=\"mm-hide-md mm-show-xs\"]/div/amp-img/@src");
			  for(DomAttr logo: ref){
			
				  System.out.println(logo.toString().substring(23,logo.toString().length()-1));	
			  }
				FilmBean films = new FilmBean();

			for(HtmlElement itema : itemss){ 
				//  System.out.println(itema.asText());

				films.setLocandina(ref.get(i).toString().substring(23,ref.get(i).toString().length()-1));
				films.setNomecinema(nomecinema);
				
				if(itema.getTextContent().contains("Regia")) {
				films.setRegia(itema.getTextContent().substring(7));
				} 
				else if(itema.getTextContent().contains("Anno")) {
					films.setAnno(Integer.parseInt(itema.getTextContent().substring(11)));
					}
				else if(itema.getTextContent().contains("Durata")) {
					films.setDurata(itema.getTextContent().substring(8));
					}
				else if(itema.getTextContent().contains("Genere")) {
					films.setGenere(itema.getTextContent().substring(8));
					}
				else if(itema.getTextContent().contains("MYmovies.it")) {
					films.setFilm(itema.getTextContent().substring(13));
					  System.out.println(i);
					film.add(films);
					films = new FilmBean();
					if(i<ref.size()-1) {
					i++;
					}
					}
				}
		}
		
}
	return film;
	}
		
	}

