package App_Code;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
/*Focuses on online searching*/

interface onlineSearch {
private static void searchItem() {
}
	WebDriver driver = new ChromeDriver();
	String search = "";
}
	
 class Walmart implements onlineSearch{
	Walmart(){
		String walmartItem = "";
		String walmartPrice = "";
	}
	void searchItem(String Item) {
		
		//search on walmart website
		search = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));
		driver.get(search);
		try {
		walmartItem = driver.findElement(By.className("css-1p4va6y eudvd6x0")).getText();
		walmartPrice = driver.findElement(By.className("css-2vqe5n esdkp3p0")).getText();
		System.out.println(walmartItem+walmartPrice);
		}
		catch{
			walmartItem = "Item not found";
			walmartPrice = "";
		}
	}
}
	
 class WholesaleClub implements onlineSearch{
	WholesaleClub(){
		String wholesaleClubPrice;
		String wholesaleClubItem;
	}
	private static void searchItem(String Item) {
		
		//search on wholesaleClub website
		search = String.format("https://www.wholesaleclub.ca/search?search-bar=%s",Item.replace(" ", "%20"));
	    driver.get(search);
	    try {
		    wholesaleClubPrice = driver.findElement(By.className("price__value selling-price-list__item__price selling-price-list__item__price--now-price__value")).getText();
		    wholesaleClubItem = driver.findElement(By.className("product-name__item product-name__item--brand")).getText() + driver.findElement(By.className("product-name__item product-name__item--name")).getText();
	    }
	    catch{
	    	wholesaleClubPrice = "";
	    	wholesaleClubItem = "Item not found";
	    }
	}
}
	
 class Dollarama implements onlineSearch{
	Dollarama(){
		String dollaramaItem;
		String dollaramaPrice;
	}
	private static void searchItem() {
		
		//search on dollarama website
		search = String.format("https://www.dollarama.com/en-CA/Search?keywords=%s",Item.replace(' ', '+'));
		driver.get(search);
		try {
		dollaramaItem = driver.findElement(By.className("js-display-name")).getText();
		dollaramaPrice = driver.findElement(By.className("product-tile-price")).getText();
		}
		catch{
		dollaramaItem = "Item not found";
		dollaramaPrice = "";
		}
	}
}
	
 class Costco implements onlineSearch {
	Costco(){
		String costcoItem;
		String costcoPrice;
	}
	private static void searchItem() {
		
		//search on costco website
		search = String.format("https://www.costco.ca/CatalogSearch?dept=All&keyword=%s",Item.replace(' ', '+'));
	    driver.get(search);
	    try {
	        costcoItem = driver.findElement(By.className("price")).getText();
	        costcoPrice = driver.findElement(By.className("description")).getText();
	    }
	    catch
	    {
	        costcoPrice = "";
	        costcoItem = "Item not Found";
	    }
		
	}
}
 class classMain{
	public static void main(String [] args) {
		Walmart w = new Walmart();
		w.searchItem("eggs");
	}
}

