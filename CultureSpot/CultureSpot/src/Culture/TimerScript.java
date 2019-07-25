package Culture;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.glass.ui.Timer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TimerScript {

	private static String[] provincie1 = {"agrigento", "alessandria", "ancona", "aosta", "arezzo", "ascoli", "asti", "avellino", "bari", 
			"barletta-andria-trani", "belluno", "benevento", "bergamo", "biella", "bologna", "bolzano", 
			"brescia", "brianza", "brindisi", "cagliari", "calabria", "caltanissetta", "campidano", "campobasso", 
			"carbonia-iglesias", "caserta", "catania", "catanzaro", "chieti", "como", "cosenza", "cremona", "crotone",
			"cuneo", "cusio", "emilia", "enna", "fermo", "ferrara", "firenze", "foggia", "forlì-cesena",
			"frosinone", "genova", "gorizia", "grosseto", "imperia", "isernia", "l'aquila" };

	private static String[] provincie2 = { "latina", "lecce", 
			"lecco", "livorno", "lodi", "lucca", "macerata", "mantova", "massa-carrara", "matera", "medio", "messina", 
			"milano", "modena", "monza", "napoli", "novara", "nuoro", "ogliastra", "olbia-tempio", "oristano", "ossola",
			"padova", "palermo", "parma", "pavia", "perugia", "pesaro", "pescara", "piacenza", "piceno", "pisa", 
			"pistoia", "pordenone", "potenza", "prato", "ragusa", "ravenna", "reggio", "rieti", "rimini", "roma", 
			"rovigo", "salerno", "sassari", "savona", "siena", "siracusa", "sondrio", "spezia", "taranto", "teramo", 
			"terni", "torino", "trapani", "trento", "treviso", "trieste", "udine", "urbino", "valentia", "varese", 
			"venezia", "verbano", "vercelli", "verona", "vibo", "vicenza", "viterbo" };

	// WRAPPERS E SCRAPERS

	public void trovaLibrerie(Connection dbCon) throws IOException, SQLException {

		URL url = new URL("https://www.librerie.it/librerie");

		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String lin = null;
		String line = null;
		String line2 = null;
		Collection<Posto> librerie = new LinkedList<Posto>();
		HtmlPage page = null;

		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  

		try { 
			String searchUrl = "https://www.librerie.it/librerie/";
			page = client.getPage(searchUrl);
		}catch(Exception e){
			e.printStackTrace();
		}

		List<HtmlAnchor> link = page.getByXPath("//ul[@id=\"lct-widget-librerie_cat\"]/li/a");
		
		if(link.isEmpty()){  
			System.out.println("No items found !");
		}else{
			for(HtmlAnchor libreri: link){
			//  System.out.println(posti.size());	
				try {  
					System.out.print(libreri.getHrefAttribute());
					 page = client.getPage(libreri.getHrefAttribute());
					System.out.println(page);	

				}catch(Exception e){
					e.printStackTrace();
				}
				
				List<HtmlElement> libre = page.getByXPath("//div[@class='post_content isotope_item_content']");
				
				if(link.isEmpty()){  
					System.out.println("No items found !");
				}else{
					int i = 0;
					for(HtmlElement libs: libre){
						
						//List<HtmlAnchor> pages = page.getByXPath("//a[@class='pager_last']");

						
						Posto posto = new Posto();
						List<HtmlElement> name = page.getByXPath("//div[@class='post_content isotope_item_content']/h4");

						List<HtmlElement> paese = page.getByXPath("//div[@class='post_content isotope_item_content']/div/p");

						posto.setNome(name.get(i).getTextContent());
						posto.setTipologia("libreria");
						String stringa = paese.get(i).getTextContent();
						if(stringa.contains("–")){
							int in3 = stringa.lastIndexOf("–");

							posto.setIndirizzo(stringa.substring(11,in3-1));
							}
						
						posto.setProvincia(libreri.getTextContent());
						
						System.out.println(posto.toString());
						i++;
						librerie.add(posto);
				}}}

				
			}
			

								/*	beans.add(bean);
									//	System.out.println(bean.toString());
								}
							}
						}
					}
				} 
			}
		} return beans;*/
	

		for (Posto lib : librerie) {
			System.out.println(" [" + lib.getNome() + ", " + lib.getProvincia() + ", " + lib.getIndirizzo() +", " 
					+ lib.getTipologia() + "]");
			this.inserisciPostiInDb(lib, dbCon);
		}

		librerie.clear();
	}

	public void trovaCinema(String arg, Connection dbCon) throws IOException, SQLException, ClassNotFoundException{

		URL url = new URL("http://trovacinema.repubblica.it/cinema/");

		URLConnection con = url.openConnection();
		InputStream is =con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String lin = null;
		String line = null;
		String code="none";
		if(arg!=null) {
			code = arg.toLowerCase();
			if(code.contains(" ")) {
				code =	code.replace(" ", "-");
			}

			Collection<Posto> cinema = new LinkedList<Posto>();		
			Collection<FilmBean> film = new LinkedList<FilmBean>();
			String places ="";
			boolean bool = false;
			boolean boole = false;
			Collection<String> placelist = new LinkedList<String>();

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

					}
				}
			}

			Iterator<?> it = placelist.iterator();
			while (it.hasNext() ) {
				String luogo = (String) it.next();
				if(luogo.contains(code) && !code.equals("none")) {

					URL url2 = new URL("http://trovacinema.repubblica.it/programmazione-cinema/citta"+luogo+"film/");
					URLConnection con2 = url2.openConnection();
					InputStream is2 =con2.getInputStream();
					BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));

					while ((line = br2.readLine()) != null) {
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

								Posto posto = new Posto();
								posto.setNome(line.substring(in1+33,in2));
								posto.setIndirizzo(code);
								posto.setProvincia(code);
								posto.setTipologia("cinema");
								cinema.add(posto); 
							}
						}
					}
				} 
			} 

			for (Posto cin : cinema) {
				System.out.println(" [" + cin.getNome() + ", " + cin.getProvincia() + ", " 
						+ cin.getIndirizzo() +", " + cin.getTipologia() + "]");

				this.inserisciPostiInDb(cin, dbCon);
				this.trovaFilm(arg, dbCon, cin.getNome());
			}

			cinema.clear();
		}
	}

	public void trovaFilm(String arg, Connection dbCon, String ncinema) throws IOException, SQLException, ClassNotFoundException{

		URL url = new URL("http://trovacinema.repubblica.it/cinema/");

		URLConnection con = url.openConnection();
		InputStream is =con.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String lin = null;
		String line = null;
		String code=arg;
		String nomecinema = ncinema;
		if(arg!=null) {
			code = arg.toLowerCase();
		}

		Collection<FilmBean> film = new LinkedList<FilmBean>();
		String places ="";
		boolean bool = false;
		boolean boole = false;
		Collection<String> placelist = new LinkedList<String>();
		HtmlPage page = null;
		int i=0;
		WebClient client = new WebClient();  
		int j=0;

		while ((lin = br.readLine()) != null) {

			if(lin.contains("<div class=\"mm-menu-colonnina mm-left\">Cerca un cinema nella tua provincia</div>") && bool != true) {
				places = lin;
				bool = true;
				int in1=162;
				int in2=240;

				while(in1 != 29102 && in1!=0 && in1!=-1) {
					placelist.add(places.substring(in1+69, in2));
					in1= places.indexOf("<a href=\"http://trovacinema.repubblica.it/programmazione-cinema/citta/",in1+1);
					in2 = places.indexOf("film/\">",in2+1);

				}
			}
		}

		Iterator<?> it = placelist.iterator();
		while (it.hasNext() ) {
			String luogo = (String) it.next();
			if(luogo.contains(code) && !code.equals("none")) {
				client.getOptions().setCssEnabled(false);  
				client.getOptions().setJavaScriptEnabled(false);  
				try {  
					String searchUrl = "http://trovacinema.repubblica.it/programmazione-cinema/citta"+luogo+"film/";
					page = client.getPage(searchUrl);
				}
				catch(Exception e){
					e.printStackTrace();
					break;
				}
			}
		}

		List<HtmlAnchor> posti = page.getByXPath("//div[@class='mm-menu-colonnina']/a");

		if(posti.isEmpty()){  
			System.out.println("No items found !");
		}else{
			for(HtmlAnchor cinema: posti){
				if( cinema.getTextContent().contains(nomecinema)) {
					try {  
						String searchUrl = cinema.getHrefAttribute();
						page = client.getPage(searchUrl);
					}
					catch(Exception e){  
						e.printStackTrace();
					}
				}
			}
		}

		List<HtmlElement> items = page.getByXPath("//div[@class='mm-padding-16 mm-col md-9']");
		if(items.isEmpty()){  
			System.out.println("No items found !");
		}else{

			List<HtmlElement> itemss = page.getByXPath("//ul[@class='mm-ul']/li");
			if(itemss.isEmpty()){  
				System.out.println("No items found !");
			}else{
				List<DomAttr>  ref =  page.getByXPath("//section[@class=\"mm-hide-md mm-show-xs\"]/div/amp-img/@src");
				if(ref.isEmpty()){  
					System.out.println("No items found !");
				}else{
					for(DomAttr logo: ref){

					}
					FilmBean films = new FilmBean();

					for(HtmlElement itema : itemss){ 
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
							film.add(films);
							films = new FilmBean();
							if(i<ref.size()-1) {
								i++;
							}
						}
					}
				}
			}
		}

		for (FilmBean oFilm : film) {
			System.out.println(" [" + oFilm.getRegia() + ", " + oFilm.getAnno() + ", " + oFilm.getDurata() 
			+ ", " + oFilm.getGenere() + ", " + oFilm.getNomecinema() + "]");
			this.InsertFilmRecensione(oFilm, dbCon);
		}

		film.clear();
	}

	public void trovaConcerti(String arg, Connection dbCon) throws IOException, SQLException, ClassNotFoundException{

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
			for(Concerto oConcert : concerti) {
				System.out.println("[" + oConcert.getNome() + ", " + oConcert.getData() + ", " 
						+ oConcert.getProvincia() + ", " + oConcert.getIndirizzo() + "]");
				this.inserisciConcertiInDb(oConcert, dbCon);
			}
			concerti.clear();
			return;
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

				
						concerto.setProvincia(cerca);
						concerto.setNome(name.get(i).getTextContent().replace("\n", "").replace("  ", ""));
						concerto.setData(day.get(i).getTextContent().replace("\n", "").replace("   ", "") + " " + month.get(i).getTextContent().replace("\n", "").replace("   ", ""));
						concerto.setIndirizzo(indirizzo.get(i).getTextContent().replace("\n", "").replace("  ", ""));
						

						i++;
						System.out.println(concerto.toString());
						concerti.add(concerto);
						
				}}j++;
		}



	}

	public void trovaTeatri(String arg, Connection dbCon) throws IOException, SQLException{

		String cerca = "none";
		if(arg!=null) {
			cerca = arg.toLowerCase();
			if(cerca.contains(" ")) {
				cerca =	cerca.replace(" ", "+");
			}

			int j=1;
			Collection<Posto> teatri = new LinkedList<Posto>();		

			for(j=1;j<17;j++) {
				URL url = new URL("https://www.teatro.it/teatri/?city="+cerca+"&order_by=more_sits&page="+j);

				URLConnection con = url.openConnection();
				con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				InputStream is =con.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String lin = null;
				Collection<String> theaterlist = new LinkedList<String>();

				while ((lin = br.readLine()) != null) {
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
						Posto post = new Posto();
						post.setNome(teatro.substring(in1+8,in2));
						post.setIndirizzo(posto.substring(0, in3));
						post.setProvincia(cerca);
						post.setTipologia("teatro");
						teatri.add(post);

						if(in1 == -1) {
							j=16;
						}
					}
				}
			}

			for(Posto ps : teatri) {
				System.out.println("[" + ps.getTipologia() + ", " + ps.getNome() +  ", " 
						+ ps.getProvincia() + ", " + ps.getIndirizzo() + "]");
				this.inserisciPostiInDb(ps, dbCon);
				this.trovaSpettacoli(arg, dbCon, ps.getNome());
			}
		}
	}	

	public void trovaSpettacoli(String arg, Connection dbCon, String nteatro) throws IOException, SQLException{

		String code = arg;
		String teatro = nteatro;
		HtmlPage page = null;

		Collection<TheatreBean> spect = new LinkedList<TheatreBean>();		
		int i = 0;
		int p=-1;

		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  

		for(int j=1;j<20;j++) {
			try {  
				String searchUrl = "https://www.teatro.it/teatri/?city="+code+"&order_by=more_sits&page="+j;
				page = client.getPage(searchUrl);
			}catch(Exception e){
				e.printStackTrace();
			}

			List<HtmlAnchor> posti = page.getByXPath("//h3[@class=\"post-title title-large\"]/a");
			if(!(posti.isEmpty())){
				for(HtmlAnchor spettacoli: posti){
					if(spettacoli.getTextContent().contains(teatro)) {
						j=19;
						try {  
							String searchUrl2 = spettacoli.getHrefAttribute();
							page = client.getPage(searchUrl2);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}

				List<HtmlElement> nspec = page.getByXPath("//div[@class='row show_layout_5 m-bottom-40']");
				if(nspec.isEmpty()){
					System.out.println("	ops, another problem...");
					return;
				}else{	
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
						spectacles.setProtagonista(details.get(p=p).getTextContent().replace("\n", "").replace("Autore:", "").replace("Regista: ", "").replace("Produttore:", "").replace("Protagonista:", "").replace("Direttore d'orchestra:", "").replace(",", ""));
						spectacles.setNometeatro(teatro);
						spect.add(spectacles);

						i++;
					}							
				}
			}else{	
				System.out.println("	ops, problem...");
				return;
			}
		}

		for(TheatreBean spettacolo : spect) {
			System.out.println("	[" + spettacolo.getSpettacolo() + ", " + spettacolo.getAutore() + ", " 
					+ spettacolo.getData() + ", " + spettacolo.getRegia() + ", " 
					+ spettacolo.getNometeatro() + "]");
			this.inserisciSpettacoliInDb(spettacolo, dbCon);
		}
		spect.clear();
	}


	

	// DATABASE RELATED



	public Collection<FilmBean> selectFilmDalDb(Connection con) throws SQLException, ClassNotFoundException {


		String querySelect = "select * from films";
		PreparedStatement select = con.prepareStatement(querySelect);
		select.execute();

		Collection<FilmBean> clx = new ArrayList<FilmBean>();
		ResultSet rs = select.getResultSet();
		while(rs.next())
		{
			FilmBean x = new FilmBean();
			x.setAnno(rs.getInt("anno"));
			x.setDurata(rs.getString("durata"));
			x.setFilm(rs.getString("film"));
			x.setGenere(rs.getString("genere"));
			x.setLocandina(rs.getString("locandina"));
			x.setNomecinema(rs.getString("nomecinema"));
			x.setRegia(rs.getString("regia"));
			x.setVoto(rs.getString("voto"));
			x.setPanoramica(rs.getString("panoramica"));
			x.setRecensione(rs.getString("recensione"));
			x.setTrama(rs.getString("trama"));

			clx.add(x);
		}

		return clx;
	}

	public void puliziaDbFilm(Connection con) throws SQLException{
		String queryPulizia = "delete from films";
		PreparedStatement pulizia = con.prepareStatement(queryPulizia);
		pulizia.execute();
	}


	public void inserisciPostiInDb(Posto obj, Connection con) {
		try{  

			String queryInserimento = " insert into posti (Nome, Provincia, Indirizzo, Tipo)"
					+ " values (?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(queryInserimento);
			preparedStmt.setString (1, obj.getNome());
			preparedStmt.setString (2, obj.getProvincia());
			preparedStmt.setString (3, obj.getIndirizzo());
			preparedStmt.setString (4, obj.getTipologia());
			preparedStmt.execute();
		}catch(Exception e){ 
			System.out.println(e);
		}
	}

	public Collection<Posto> selectPostiDalDb(String str, String post, Connection con) throws SQLException, ClassNotFoundException {

		String querySelect = "select * from posti where posti.Tipo = ? and posti.Provincia = ?";
		PreparedStatement select = con.prepareStatement(querySelect);
		select.setString(1, str);
		select.setString(2, post);
		select.execute();

		Collection<Posto> clx = new ArrayList<Posto>();
		ResultSet rs = select.getResultSet();
		while(rs.next())
		{
			Posto x = new Posto();
			x.setIndirizzo(rs.getString("Indirizzo"));
			x.setNome(rs.getString("Nome"));
			x.setProvincia(rs.getString("Provincia"));
			x.setTipologia(rs.getString("Tipo"));

			clx.add(x);
		}

		return clx;
	}

	public void puliziaDbPosti(Connection con, String str) throws SQLException {
		String queryPulizia = "delete from posti where posti.Tipo = ?";
		PreparedStatement pulizia = con.prepareStatement(queryPulizia);
		pulizia.setString(1, str);
		pulizia.execute();
	}


	public void inserisciConcertiInDb(Concerto obj, Connection con) {
		try{  

			String queryInserimento = " insert into concerti (Nome, Provincia, Indirizzo, Data)"
					+ " values (?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(queryInserimento);
			preparedStmt.setString (1, obj.getNome());
			preparedStmt.setString (2, obj.getProvincia());
			preparedStmt.setString (3, obj.getIndirizzo());
			preparedStmt.setString (4, obj.getData());
			preparedStmt.execute();
		}catch(Exception e){ 
			System.out.println(e);
		}
	}

	public Collection<Concerto> selectConcertiDalDb(String str, Connection con) throws SQLException, ClassNotFoundException {

		String querySelect = "select * from concerti where concerti.Provincia = ?";
		PreparedStatement select = con.prepareStatement(querySelect);
		select.setString(1, str);
		select.execute();

		Collection<Concerto> clx = new ArrayList<Concerto>();
		ResultSet rs = select.getResultSet();
		while(rs.next())
		{
			Concerto x = new Concerto();
			x.setNome(rs.getString("Nome"));
			x.setIndirizzo(rs.getString("Indirizzo"));
			x.setData(rs.getString("Data"));
			x.setProvincia(rs.getString("Provincia"));

			clx.add(x);
		}

		return clx;
	}

	public void puliziaDbConcerti(Connection con) throws SQLException {
		String queryPulizia = "delete from concerti";
		PreparedStatement pulizia = con.prepareStatement(queryPulizia);
		pulizia.execute();
	}


	public void inserisciSpettacoliInDb(TheatreBean obj, Connection con) {
		try{  
			String queryInserimento = "insert into spettacoli(spettacolo, regia, data, autore, protagonista, produttore, locandina, nometeatro)"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(queryInserimento);
			preparedStmt.setString (1, obj.getSpettacolo());
			preparedStmt.setString (2, obj.getRegia());
			preparedStmt.setString (3, obj.getData());
			preparedStmt.setString (4, obj.getAutore());
			preparedStmt.setString (5, obj.getProtagonista());
			preparedStmt.setString (6, obj.getProduttore());
			preparedStmt.setString (7, obj.getLocandina());
			preparedStmt.setString (8, obj.getNometeatro());
			preparedStmt.execute();
		}catch(Exception e){ 
			System.out.println(e);
		}
	}

public void InsertFilmRecensione(FilmBean obj,Connection dbCon) throws IOException, SQLException, ClassNotFoundException{
		
		Collection<FilmBean> listaRitorno = new ArrayList<FilmBean>();
	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/culturespot","root","");
		

		
			FilmBean rev = ReviewWrapper.RevWrapper(obj.getFilm());
			obj.setVoto(rev.getVoto());
		obj.setPanoramica(rev.getPanoramica());
		obj.setRecensione(rev.getRecensione());
		obj.setFilm(rev.getFilm());

		obj.setTrama(rev.getTrama());
		
		try{  
			String queryInserimento = "insert into films(film, regia, anno, durata, genere, locandina, nomecinema,voto, trama, panoramica, recensione)"
					+ "values (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(queryInserimento);
			preparedStmt.setString (1, obj.getFilm());
			preparedStmt.setString (2, obj.getRegia());
			preparedStmt.setInt    (3,obj.getAnno());
			preparedStmt.setString (4, obj.getDurata());
			preparedStmt.setString (5, obj.getGenere());
			preparedStmt.setString (6, obj.getLocandina());
			preparedStmt.setString (7, obj.getNomecinema());
			preparedStmt.setString (8, obj.getVoto());
			preparedStmt.setString (9, obj.getTrama());
			preparedStmt.setString (10, obj.getPanoramica());
			preparedStmt.setString (11, obj.getRecensione());
			preparedStmt.execute();
		}catch(Exception e){ 
			System.out.println(e);
		}
			System.out.println(listaRitorno);
			
 
	}
	
	
	
	public Collection<TheatreBean> selectSpettacoliDalDb(Connection con) throws SQLException, ClassNotFoundException {

		String querySelect = "select * from spettacoli";
		PreparedStatement select = con.prepareStatement(querySelect);
		select.execute();

		Collection<TheatreBean> clx = new ArrayList<TheatreBean>();
		ResultSet rs = select.getResultSet();
		while(rs.next())
		{
			TheatreBean x = new TheatreBean();
			x.setSpettacolo(rs.getString("spettacolo"));
			x.setAutore(rs.getString("autore"));
			x.setLocandina(rs.getString("locandina"));
			x.setNometeatro(rs.getString("nometeatro"));
			x.setProduttore(rs.getString("produttore"));
			x.setProtagonista(rs.getString("protagonista"));
			x.setRegia(rs.getString("regia"));
			x.setData(rs.getString("data"));

			clx.add(x);
		}

		return clx;
		
	}

	public void puliziaDbSpettacoli(Connection con) throws SQLException {
		String queryPulizia = "delete from spettacoli";
		PreparedStatement pulizia = con.prepareStatement(queryPulizia);
		pulizia.execute();
	}



	public Collection<FilmBean> joinCinemaFilm(String prov, Connection dbCon) throws IOException, SQLException{

		Collection<FilmBean> listaRitorno = new ArrayList<FilmBean>();

		Statement queryCinema = dbCon.createStatement();
		ResultSet rs = queryCinema.executeQuery(  
				" SELECT films.*, posti.*"
						+ " FROM films "
						+ " JOIN posti "
						+ " ON posti.Nome = films.nomecinema" 
						+ " WHERE posti.Provincia = '" + prov + "'");

		String cinms = "";
		Integer number = 1;
		while(rs.next())
		{
			if(!(cinms.equals(rs.getString("nome")))) {
				System.out.println("");
				System.out.println(" | " + rs.getString("nome") + " | " + "(" + rs.getString("provincia") + ")" );
				cinms = (String) rs.getString("nome");
			}

			System.out.println( "   " + number + " [ " + rs.getString("film") + ", " + 
					rs.getString("regia") + ", " + 
					rs.getString("genere") +  ", " +
					rs.getString("durata") + " ]"); 
			number = number + 1;

			FilmBean g = new FilmBean();
			g.setFilm(rs.getString("film"));
			g.setDurata(rs.getString("durata"));
			g.setAnno(rs.getInt("anno"));
			g.setGenere(rs.getString("genere"));
			g.setLocandina(rs.getString("locandina"));
			g.setRegia(rs.getString("regia"));
			g.setNomecinema(rs.getString("nome"));
			listaRitorno.add(g);
		}
		
		return listaRitorno;
	}


/*	public Collection<FilmBean> joinFilmRecensione(Connection dbCon, Collection<FilmBean> film ) throws IOException, SQLException{
		
		Collection<FilmBean> listaRitorno = new ArrayList<FilmBean>();

		if (film != null && film.size() != 0) {
			Iterator<?> ito = film.iterator();
			while (ito.hasNext()) {
				FilmBean films = (FilmBean) ito.next();
		
		{
			try {
		Statement queryCinema = dbCon.createStatement();
		ResultSet rs = queryCinema.executeQuery(  
				" SELECT recensioni.*"
						+ " FROM recensioni"
						+ " JOIN films " 
						+ " ON films.film = recensioni.film" 
						+ " WHERE films.film = '" + films.getFilm() + "'");
		
		if(rs != null) {
		rs.first();

			films.setVoto(rs. getString("voto"));
			films.setPanoramica(rs.getString("Panoramica"));
			films.setRecensione(rs.getString("Recensione"));
			films.setFilm(rs.getString("film"));
			films.setTrama(rs.getString("Trama"));

			listaRitorno.add(films);
			
		}
		}catch(Exception e)	{}
		 
		
			/*	
			FilmBean rev = ReviewWrapper.RevWrapper(films.getFilm());
			films.setVoto(rev.getVoto());
		films.setPanoramica(rev.getPanoramica());
		films.setRecensione(rev.getRecensione());
		films.setFilm(rev.getFilm());

		films.setTrama(rev.getTrama());

						listaRitorno.add(films);
		
				
		
			System.out.println(listaRitorno);
			String queryInserimento = "insert into recensioni(film, voto, Trama, Panoramica, Recensione)"
					+ "values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = dbCon.prepareStatement(queryInserimento);
			preparedStmt.setString (1, films.getFilm());
			preparedStmt.setString (2, films.getVoto());
			preparedStmt.setString (3, films.getTrama());
			preparedStmt.setString (4, films.getPanoramica());
			preparedStmt.setString (5, films.getRecensione());
		
			preparedStmt.execute();

	
			} }}}
		return listaRitorno;
	}*/

	public Collection<TheatreBean> joinTeatriSpettacoli(String prov, Connection dbCon) throws IOException, SQLException{
		
		Collection<TheatreBean> listaRitorno = new ArrayList<TheatreBean>();
		
		Statement queryCinema = dbCon.createStatement();
		ResultSet rs = queryCinema.executeQuery(  
				" SELECT spettacoli.*, posti.* "
						+ " FROM spettacoli "
						+ " JOIN posti "
						+ " ON posti.Nome = spettacoli.nometeatro" 
						+ " WHERE posti.Provincia = '" + prov + "'");


		String teatrs = "";
		Integer number = 1;
		while(rs.next())
		{

			if(!(teatrs.equals(rs.getString("nome")))) {
				System.out.println("");
				System.out.println(" | " + rs.getString("nome") + " | " + "(" + rs.getString("provincia") + ")" );
				teatrs = (String) rs.getString("nome");
			}

			System.out.println( "   " + number + " [ " + rs.getString("spettacolo") + ", " +
					rs.getString("data") + ", " + 
					rs.getString("regia") + ", " + 
					rs.getString("autore") +  ", " +
					rs.getString("produttore") +  ", " +
					rs.getString("protagonista") + " ]"); 
			number = number + 1;
			
			TheatreBean t = new TheatreBean();
			t.setAutore(rs.getString("autore"));
			t.setData(rs.getString("data"));
			t.setLocandina(rs.getString("locandina"));
			t.setNometeatro(rs.getString("nome"));
			t.setProduttore(rs.getString("produttore"));
			t.setProtagonista(rs.getString("protagonista"));
			t.setRegia(rs.getString("regia"));
			t.setSpettacolo(rs.getString("spettacolo"));
			listaRitorno.add(t);
		}
		
		return listaRitorno;
	}

	public Collection<Posto> joinTeatriSoloConSpettacoli(String prov, Connection dbCon) throws IOException, SQLException{
		
		Collection<Posto> listaRitorno = new ArrayList<Posto>();
		
		Statement queryCinema = dbCon.createStatement();
		ResultSet rs = queryCinema.executeQuery(  
				" SELECT DISTINCT posti.* "
						+ " FROM posti "
						+ " JOIN spettacoli "
						+ " ON posti.Nome = spettacoli.nometeatro" 
						+ " WHERE posti.Tipo= 'teatro'"
						+ " AND posti.Provincia = '" + prov + "'");

		while(rs.next())
		{
			Posto p = new Posto();
			p.setNome(rs.getString("nome"));
			p.setIndirizzo(rs.getString("indirizzo"));
			p.setProvincia(rs.getString("provincia"));
			p.setTipologia("teatro");
			listaRitorno.add(p);
		}
		
		return listaRitorno;
	}
	
public Collection<Posto> joinCinemaSoloConFilm(String prov, Connection dbCon) throws IOException, SQLException{
		
		Collection<Posto> listaRitorno = new ArrayList<Posto>();
		
		Statement queryCinema = dbCon.createStatement();
		ResultSet rs = queryCinema.executeQuery(  
				" SELECT DISTINCT posti.* "
						+ " FROM posti "
						+ " JOIN films "
						+ " ON posti.Nome = films.nomecinema" 
						+ " WHERE posti.Tipo = 'cinema'"
						+ " AND posti.Provincia = '" + prov + "'");

		while(rs.next())
		{
			Posto p = new Posto();
			p.setNome(rs.getString("nome"));
			p.setIndirizzo(rs.getString("indirizzo"));
			p.setProvincia(rs.getString("provincia"));
			p.setTipologia("cinema");
			listaRitorno.add(p);
		}
		
		return listaRitorno;
	}
	
	
	//AVVIATORE TIMER E RIEMPITORE DATABASE

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		TimerScript x = new TimerScript();
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/culturespot","root","");

		ScheduledExecutorService esecutore = Executors.newScheduledThreadPool(1);

		Runnable taskConcerti = () -> {
			try {
				x.puliziaDbConcerti(con);

				for(String y : provincie1) {
					x.trovaConcerti(y, con);
				}
				for(String y : provincie2) {
					x.trovaConcerti(y, con);
				}

			} catch (IOException | SQLException e) { e.printStackTrace(); } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		Runnable taskCinemaFilm = () -> {
			try {
				x.puliziaDbPosti(con, "cinema");
				x.puliziaDbFilm(con);

				for(String y : provincie1) {
					x.trovaCinema(y, con);
				}
				for(String y : provincie2) {
					x.trovaCinema(y, con);
				}

			} catch (IOException | SQLException e) { e.printStackTrace(); } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		Runnable taskLibrerie = () -> {
			try {
				x.puliziaDbPosti(con, "libreria");

					x.trovaLibrerie(con);
				
				

			} catch (IOException | SQLException e) { e.printStackTrace(); }
		};

		Runnable taskTeatriSpettacoli = () -> {
			try {
				x.puliziaDbPosti(con, "teatro");
				x.puliziaDbSpettacoli(con);

				for(String y : provincie1) {
					x.trovaTeatri(y, con);
				}
				for(String y : provincie2) {
					x.trovaTeatri(y, con);
				}

			} catch (IOException | SQLException e) { e.printStackTrace(); }
		};
		
		
		esecutore.scheduleWithFixedDelay(taskConcerti, 0, 10, TimeUnit.DAYS);
		//esecutore.scheduleWithFixedDelay(taskCinemaFilm, 0, 6, TimeUnit.DAYS);
		//esecutore.scheduleWithFixedDelay(taskLibrerie, 0, 50, TimeUnit.DAYS);
		//esecutore.scheduleWithFixedDelay(taskTeatriSpettacoli, 0, 30, TimeUnit.DAYS);

		//x.puliziaDbConcerti(con);
		//x.puliziaDbFilm(con);
		//x.puliziaDbPosti(con, "libreria");
		//
		//x.puliziaDbPosti(con, "teatro");
		// x.puliziaDbSpettacoli(con);


		//for(String y : primo) {
		//x.trovaConcerti(y, con);
		//x.trovaCinema(y, con);
		//x.trovaLibrerie(y, con);
		//x.trovaTeatri(y, con);
		//}

		//x.joinTeatriSpettacoli("milano", con);

	}

}



