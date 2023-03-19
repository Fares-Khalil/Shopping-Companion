package App_Code;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import java.net.*;
import java.io.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/*Focuses on online searching*/

	//System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\faris\\Downloads\\chromedriver.exe\"");

interface onlineSearch {
	public String searchItem(String Item);
	
}
	
class Walmart implements onlineSearch{
	String walmartItem;
	String walmartPrice;
	String search;
	
	Walmart(){
		search = " ";
	}
	public String searchItem(String Item) {
		/*
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-infobars");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(options);
		*/
		

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
		//driver.close();
		return walmartPrice;
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
	public String searchItem(String Item) {
		WebDriver driver = new ChromeDriver();
		//search on dollarama website
		search = String.format("https://www.dollarama.com/en-CA/Search?keywords=%s",Item.replace(' ', '+'));
		driver.get(search);
		try {
		dollaramaItem = driver.findElement(By.className("js-display-name")).getText();
		dollaramaPrice = driver.findElement(By.className("product-tile-price")).getText();
		}
		catch(Exception e){
		dollaramaItem = "Item not found";
		dollaramaPrice = "";
		}
		return dollaramaPrice;
	}
}
	
	
class Costco implements onlineSearch {
	String costcoItem;
	String costcoPrice;
	String search;
	Costco(){
		search = " ";
	}
	public String searchItem(String Item) {
		
		//search on costco website
		search = String.format("https://www.costco.ca/CatalogSearch?dept=All&keyword=%s",Item.replace(' ', '+'));
		try {
			Document document  = Jsoup.connect(search).get();
	    	Element link = document.select("div.price").first();
	
	    	costcoPrice = link.html();
	    	link = document.select("div.description").first();
	    	
	    	costcoItem = link.html();
	    }
	    catch(Exception e)
	    {
	        costcoPrice = "";
	        costcoItem = "Item not Found";
	    }
	
		return costcoPrice;
	}
}
	
class WholesaleClub implements onlineSearch{
	String wholesaleClubPrice;
	String wholesaleClubItem;
	String search;
	WholesaleClub(){
		search = " ";
	}
	public String searchItem(String Item) {
		WebDriver driver = new ChromeDriver();
		//search on wholesaleClub website
		search = String.format("https://www.wholesaleclub.ca/search?search-bar=%s",Item.replace(" ", "%20"));
	    driver.get(search);
		Document document=null;
		try {
			document = Jsoup.connect("https://www.wholesaleclub.ca/search?search-bar=milk").get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Object el:document.select("*")) {
	    	 System.out.println(el);

		}
    	Element link = document.select("*").last();
    	 System.out.println("Outer HTML: " + link.outerHtml());
	        System.out.println("Inner HTML: " + link.html());
	        wholesaleClubPrice = driver.findElement(By.className("price__value selling-price-list__item__price selling-price-list__item__price--now-price__value")).getText();
		    wholesaleClubItem = driver.findElement(By.className("product-name__item product-name__item--brand")).getText() + driver.findElement(By.className("product-name__item product-name__item--name")).getText();
	    try {
	    	//Document document  = Jsoup.connect(search).get();
	    	//Element link = document.select("div").get(5);
	        System.out.println("Outer HTML: " + link.outerHtml());
	        System.out.println("Inner HTML: " + link.html());
	    	wholesaleClubPrice = link.html();
	    	link = document.select("div.product-name__item product-name__item--brand").first();
	    	
	    	wholesaleClubItem = link.html();
		    wholesaleClubPrice = driver.findElement(By.className("price__value selling-price-list__item__price")).getText();
		    wholesaleClubItem = driver.findElement(By.className("product-name__item product-name__item--brand")).getText() + driver.findElement(By.className("product-name__item product-name__item--name")).getText();
	    }
	    catch(Exception e){
	    	wholesaleClubPrice = "";
	    	wholesaleClubItem = "Item not found";
	    }
	    return wholesaleClubPrice;
	}
	
}
	

	

	

