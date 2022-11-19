package web_scraping;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * @author Yeury de los Santos
 * web scraper extracting information from daft.ie
 *
 */

public class web_scraping {


	public static void main(String[] args) {
		
		ArrayList<String> address = new ArrayList<>();
		ArrayList<String> area = new ArrayList<>();
		ArrayList<String> price = new ArrayList<>();
		
		try {
			Document data = Jsoup.connect("https://www.daft.ie/property-for-sale/dublin-city?pageSize=20&from=0").get();
			Elements tags = data.getElementsByAttributeValue("data-testid","floor-area");
			
			//website html tags
			System.out.println(data.outerHtml());
			
			//looping html tags and filling up the lists
			for(Element x : tags) {
				
				if(x.parent()!=null) {
					address.add(data.getElementsByAttributeValue("data-testid", "address").text());
					area.add(data.getElementsByAttributeValue("data-testid", "floor-area").text());
					
					//price not clean
					//price.add(data.getElementsByAttributeValue("data-testid", "price").text());
					
					//price clean
					
					//no symbols and converted to integer
					price.add(data.getElementsByAttributeValue("data-testid", "price").text().replace("€", "").replace(",",""));
				}
			}
			
			System.out.println("\n"+address);
			System.out.println("\n"+area);
			System.out.println("\n"+price);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
