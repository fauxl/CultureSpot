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
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ConcertWrapper {

	public static Collection<Concerto> main(String arg) throws IOException {

		// Make a URL to the web page

		String cerca = "none";
		if(arg!=null) {
			cerca = arg;
			if(cerca.contains(" ")) {
				cerca =	cerca.replace(" ", "+");
			}
		} 

		Collection<Concerto> concerti =  new LinkedList<Concerto>();

		
		Collection<Bean> beans = new LinkedList<Bean>();
		HtmlPage page = null;


		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		int j=1;

		for(;;) {
		try { 
			String searchUrl = ("https://www.rockol.it/concerti-ricerca?artista=&citta="+cerca+"&dataDD=&dataMM=&dataYYYY=&locale=&provincia=&regione=&pag="+j);

			page = client.getPage(searchUrl);
		}catch(Exception e){
			e.printStackTrace();
			return concerti;
		}

		//div[@class="mod-md-title concert-list-artist-name"]
		
		List<HtmlElement> elements = page.getByXPath("//div[@class=\"row mod-sm-gutter concert-list-desktop-picture-row\"]");
		
		if(elements.isEmpty()){  
			System.out.println("No items found !");
		}else{
			List<HtmlElement> name = page.getByXPath("	//div[@class='mod-md-title concert-list-artist-name']" );
			List<HtmlElement> indirizzo = page.getByXPath("//h4[@class=\"mod-md-title mod-normal-case\"]");
			List<HtmlElement> month = page.getByXPath("//div[@class=\"concert-list-desktop-picture-month\"]");
			List<HtmlElement> day = page.getByXPath("//div[@class=\"concert-list-desktop-picture-container-inner\"]/div[2]");

			int i = 0;

			for(HtmlElement el: elements){
			//  System.out.println(posti.size());	
				Concerto concerto = new Concerto();

				
						concerto.setProvincia("napoli");
						concerto.setNome(name.get(i).getTextContent().replace("\n", "").replace("  ", ""));
						concerto.setData(day.get(i).getTextContent().replace("\n", "").replace("   ", "") + " " + month.get(i).getTextContent().replace("\n", "").replace("   ", ""));
						concerto.setIndirizzo(indirizzo.get(i).getTextContent().replace("\n", "").replace("  ", ""));
						

						i++;
						System.out.println(concerto.toString());
						concerti.add(concerto);
						
				}}j++;
		}

	}
		
		

	/*	URL url = new URL("https://www.rockol.it/concerti-ricerca?artista=&citta="+cerca+"&dataDD=&dataMM=&dataYYYY=&locale=&provincia=&regione=");

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

		}return musei;*/
	}


















