package Culture;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ReviewWrapper {
	public static FilmBean RevWrapper(String film) throws IOException  {

		String cerca = "none";
		/*if(arg!=null) {
		cerca = arg.toLowerCase();
		System.out.println(cerca);
		if(cerca.contains(" ")) {
			cerca =	cerca.replace(" ", "+");
		}*/

		String code = film.replace(" ", "%20");
		HtmlPage page = null;

		FilmBean rev = new FilmBean();		
		int i = 0;
		int p=-1;


		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  

		try { 
			String searchUrl = "https://www.comingsoon.it/film/?titolo="+code;
			page = client.getPage(searchUrl);
		}catch(Exception e){
			e.printStackTrace();
		}

		List<HtmlAnchor> link = page.getByXPath("//a[@class=\"cards cards-horiz-box box-office min film\"]");

		if(link.isEmpty()){  
			System.out.println("No items found !");
		}else{
			//  System.out.println(posti.size());	
				try {  
					String searchUrl2 = link.get(0).getHrefAttribute();
					page = client.getPage("https://www.comingsoon.it/"+searchUrl2);
					System.out.println(page);	

				}catch(Exception e){
					e.printStackTrace();
				}
			}

		List<HtmlElement> details = page.getByXPath("//div[@class='mrl']/span");

		List<HtmlElement> review = page.getByXPath("//h2[@class=\"h2 anchor\"]/..");
		

		
		if(details.size()!=0) {

		rev.setVoto(details.get(0).getTextContent());
		}
		if(review.size()>3) {
		rev.setPanoramica(review.get(1).getTextContent());
		rev.setRecensione(review.get(2).getTextContent());
		rev.setTrama(review.get(0).getTextContent());
		rev.setFilm(film);
		}
		else if(review.size()>2) {
			rev.setRecensione(review.get(1).getTextContent());
			rev.setTrama(review.get(0).getTextContent());
			rev.setFilm(film);

			}
			
		
		System.out.println(rev);
		return rev;

	}
}

