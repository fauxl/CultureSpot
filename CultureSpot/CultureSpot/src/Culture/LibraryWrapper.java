package Culture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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


public class LibraryWrapper {

	public static void  main(String[] args) throws IOException {


		String lin = null;
		String line = null;
		String line2 = null;
		//String code = ">"+arg+"<";
		Collection<Bean> beans = new LinkedList<Bean>();
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
			for(HtmlAnchor librerie: link){
			//  System.out.println(posti.size());	
				try {  
					System.out.print(librerie.getHrefAttribute());
					 page = client.getPage(librerie.getHrefAttribute());
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
						posto.setProvincia(librerie.getTextContent());

						System.out.println(posto.toString());
						i++;
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
	}
}