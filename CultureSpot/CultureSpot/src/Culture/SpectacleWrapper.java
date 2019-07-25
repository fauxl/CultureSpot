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

public class SpectacleWrapper {
	public static Collection<TheatreBean> Wrapper(String arg,String nteatro) throws IOException  {

		String cerca = "none";
		/*if(arg!=null) {
		cerca = arg.toLowerCase();
		System.out.println(cerca);
		if(cerca.contains(" ")) {
			cerca =	cerca.replace(" ", "+");
		}*/

		String code = arg;
		String teatro = nteatro;
		HtmlPage page = null;
			
		Collection<TheatreBean> spect = new LinkedList<TheatreBean>();		
		int i = 0;
		int p=-1;

		for(int j=1;j<40;j++) {

			WebClient client = new WebClient();  
			client.getOptions().setCssEnabled(false);  
			client.getOptions().setJavaScriptEnabled(false);  

			try {  

				String searchUrl = "https://www.teatro.it/teatri/?city="+code+"&order_by=more_sits&page="+j;
				page = client.getPage(searchUrl);
			}catch(Exception e){
				e.printStackTrace();
			}

			List<HtmlAnchor> posti = page.getByXPath("//h3[@class=\"post-title title-large\"]/a");
			if(posti.isEmpty()){  
				System.out.println("No items found !");
				j=39;
			}else{
				//  System.out.println(posti.size());	
				for(HtmlAnchor spettacoli: posti){
					if(spettacoli.getTextContent().contains(teatro)) {
						j=39;
						System.out.println(spettacoli.getTextContent());
						try {  
							String searchUrl2 = spettacoli.getHrefAttribute();
							page = client.getPage(searchUrl2);
							
						}catch(Exception e){
							e.printStackTrace();
						}
					}}}}
					
						List<HtmlElement> nspec = page.getByXPath("//div[@class='row show_layout_5 m-bottom-40']");
						if(nspec.isEmpty()){  
							System.out.println("No items found !");
						}else{
							System.out.println(nspec.size());	
							for(HtmlElement spec: nspec){
								TheatreBean spectacles = new TheatreBean();

								List<DomAttr> locandina = page.getByXPath("//div[@class=\"row show_layout_5 m-bottom-40\"]/div/div/div/a/img/@src");
								List<HtmlAnchor> nome = page.getByXPath("//div[@class=\"row show_layout_5 m-bottom-40\"]/div/h3/a");
								List<HtmlElement> data = page.getByXPath("//div[@class=\"row show_layout_5 m-bottom-40\"]/div/div/small/span");
								List<HtmlElement> details = page.getByXPath("//div[@class='row show_layout_5 m-bottom-40']/div/div/div/div/div");

								spectacles.setLocandina(locandina.get(i).getTextContent());
								spectacles.setSpettacolo(nome.get(i).getTextContent());
								spectacles.setData(data.get(i).getTextContent().replace("\n", "").replace(" ", ""));
								spectacles.setProduttore(details.get(p=p+1).getTextContent().replace("\n", "").replace("Produttore:", "").replace("Gruppo:", "").replace(",", ""));
								spectacles.setRegia(details.get(p=p+1).getTextContent().replace("\n", "").replace("Regista:", "").replace("Autore:", "").replace("Produttore:", "").replace("Musicista:","").replace("Artista:","").replace(",", ""));
								spectacles.setAutore(details.get(p=p+1).getTextContent().replace("\n", "").replace("Autore:", "").replace("Regista: ", "").replace("Produttore:", "").replace("Coreografo:","").replace("Orchestra:","").replace(",", ""));
								spectacles.setProtagonista(details.get(p=p+1).getTextContent().replace("\n", "").replace("Autore:", "").replace("Regista: ", "").replace("Produttore:", "").replace("Protagonista:", "").replace("Direttore d'orchestra:", "").replace(",", ""));
								spectacles.setNometeatro(teatro);
								spect.add(spectacles);

								i++;
							}							


		}
		return spect;
	}
}

