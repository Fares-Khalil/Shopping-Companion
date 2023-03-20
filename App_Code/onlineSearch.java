package App_Code;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import java.net.*;
import java.io.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import us.codecraft.xsoup.Xsoup;

/*Focuses on online searching*/

	//System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\faris\\Downloads\\chromedriver.exe\"");

interface onlineSearch {
	public String[] searchItem(String Item);
}
	
class Walmart implements onlineSearch{
	String walmartItem;
	String walmartPrice;
	String search;
	
	Walmart(){
		search = " ";
	}
	public String[] searchItem(String Item) {
		/*
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-infobars");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(options);
		*/
		//walmartItem = driver.findElement(By.xpath("//*[@id=\"product-results\"]/div[1]/div/a/div/div[2]/div[2]/span/div/p")).getText();
		//walmartPrice = driver.findElement(By.xpath("//*[@id=\"product-results\"]/div[1]/div/a/div/div[2]/div[3]/div/div/span/span")).getText();

		String[] result = {"",""};

		//search on walmart website
		search = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));

		try {
			Document document  = Jsoup.connect(search).get();
	    	Element link = document.select("div.css-1p4va6y eudvd6x0").first();

	    	walmartPrice = link.html();
	    	link = document.select("div.css-2vqe5n esdkp3p0").first();
	    	
	    	walmartItem = link.html();
	    	
	    	
	    	
	        System.out.println("Outer HTML: " + link.outerHtml());
	        System.out.println("Inner HTML: " + link.html());

		    //walmartItem = driver.findElement(By.className("css-1p4va6y eudvd6x0")).getText();
			//walmartPrice = driver.findElement(By.className("css-2vqe5n esdkp3p0")).getText();
		Thread.sleep(10);
		}
		catch(Exception e){
			walmartItem = "Item not found";
			walmartPrice = "";
		}
		result[0] = walmartItem;
		result[1] =  walmartPrice;
		//driver.close();
		return result;
	}
}
	
	//search on sobeys website
	
class Dollarama implements onlineSearch{
	String dollaramaItem;
	String dollaramaPrice;
	String search;
	Dollarama(){
		search = " ";
	}
	public String[] searchItem(String Item) {
		
		String[] result = {"",""};
		//search on dollarama website
		search = String.format("https://www.dollarama.com/en-CA/Search?keywords=%s",Item.replace(' ', '+'));
		try {
			Document document  = Jsoup.connect(search).get();
	    	Element link = document.select(".product-tile-price").first();
	
	    	dollaramaPrice = link.html().substring(link.html().indexOf('$'),link.html().indexOf("</span>"));
	    	link = document.select(".js-display-name").first();
	    	
	    	dollaramaItem = link.html().replace("<br>", " ");

		//dollaramaItem = driver.findElement(By.className("js-display-name")).getText();
		//dollaramaPrice = driver.findElement(By.className("product-tile-price")).getText();
		}
		catch(Exception e){
		dollaramaItem = "Item not found";
		dollaramaPrice = "";
		}
		result[0] = dollaramaItem;
		result[1] =  dollaramaPrice;
		return result;
	}
}
	
	
class Costco implements onlineSearch {
	String costcoItem;
	String costcoPrice;
	String search;
	Costco(){
		search = " ";
	}
	public String[] searchItem(String Item) {
		String[] result = {"",""};

		//search on costco website
		search = String.format("https://www.costco.ca/CatalogSearch?dept=All&keyword=%s",Item.replace(' ', '+'));
		try {
			Document document  = Jsoup.connect(search).get();
	    	Element link = document.select(".price").first();
	
	    	costcoPrice = link.html();
	    	link = document.select(".description").first();
	    	
	    	costcoItem = link.html().substring( link.html().indexOf(">")+1,link.html().indexOf("</a>"));
	    }
	    catch(Exception e)
	    {
	        costcoPrice = "";
	        costcoItem = "Item not Found";
	    }
		result[0] = costcoItem;
		result[1] =  costcoPrice;
		return result;
	}
}
	
class WholesaleClub implements onlineSearch{
	String wholesaleClubPrice;
	String wholesaleClubItem;
	String search;
	WholesaleClub(){
		search = " ";
	}
	public String[] searchItem(String Item) {
		String[] result = {"",""};

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("headless");

		WebDriver driver = new ChromeDriver(opt);
		//search on wholesaleClub website
		search = String.format("https://www.wholesaleclub.ca/search?search-bar=%s",Item.replace(" ", "%20"));
		            
	    try {
	    	driver.get(search);
	     	while(!driver.getTitle().contains("Search")) {
	     	}
	     	Thread.sleep(10);
	    	wholesaleClubPrice = driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[3]/div[1]/div/div[3]/div/div/span/span[1]")).getText();
		    wholesaleClubItem = driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[3]/div[1]/h3/a/span/span[2]")).getText() + driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[3]/div[1]/h3/a/span/span[3]")).getText();

	    }
	    catch(Exception e){
	    	wholesaleClubPrice = "";
	    	wholesaleClubItem = "Item not found";
	    }
	    result[0] = wholesaleClubItem;
		result[1] =  wholesaleClubPrice;
	    return result;
	}
	
}
	

	

	

