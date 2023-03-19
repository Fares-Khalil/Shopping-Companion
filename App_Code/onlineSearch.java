package App_Code;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
/*Focuses on online searching*/

	//System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\faris\\Downloads\\chromedriver.exe\"");

interface onlineSearch {
	public String searchItem(String Item);
	WebDriver driver = new ChromeDriver();
	
}
	
class Walmart implements onlineSearch{
	String walmartItem;
	String walmartPrice;
	String search;
	Walmart(){
		search = " ";
	}
	public String searchItem(String Item) {
		
		//search on walmart website
		search = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));
		driver.get(search);
		try {
		walmartItem = driver.findElement(By.className("css-1p4va6y eudvd6x0")).getText();
		walmartPrice = driver.findElement(By.className("css-2vqe5n esdkp3p0")).getText();
		}
		catch(Exception e){
			walmartItem = "Item not found";
			walmartPrice = "";
		}
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
	    driver.get(search);
	    try {
	        costcoItem = driver.findElement(By.className("price")).getText();
	        costcoPrice = driver.findElement(By.className("description")).getText();
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
		
		//search on wholesaleClub website
		search = String.format("https://www.wholesaleclub.ca/search?search-bar=%s",Item.replace(" ", "%20"));
	    driver.get(search);
	    try {
		    wholesaleClubPrice = driver.findElement(By.className("price__value selling-price-list__item__price selling-price-list__item__price--now-price__value")).getText();
		    wholesaleClubItem = driver.findElement(By.className("product-name__item product-name__item--brand")).getText() + driver.findElement(By.className("product-name__item product-name__item--name")).getText();
	    }
	    catch(Exception e){
	    	wholesaleClubPrice = "";
	    	wholesaleClubItem = "Item not found";
	    }
	    return wholesaleClubPrice;
	}
	
}
	

	

	

