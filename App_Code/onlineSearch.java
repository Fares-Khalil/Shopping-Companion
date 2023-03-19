package App_Code;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
/*Focuses on online searching*/

public class onlineSearch {
private static void searchItem(String Item){
	WebDriver driver = new ChromeDriver();
	
	//search on walmart website
	String search0 = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));
	driver.get(search0);
	String walmartItem = driver.findElement(By.className("css-1p4va6y eudvd6x0")).getText();
	
	//search on wholesaleClub website
	String search1 = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));
	driver.get(search1);
	WebElement firstmatch1 = driver.findElement(By.className("css-1p4va6y eudvd6x0"));
	String sobeysItem = firstmatch1.getText();
	
	//search on dollarama website
	String search2 = String.format("https://www.dollarama.com/en-CA/Search?keywords=%s",Item.replace(' ', '+'));
	driver.get(search2);
	WebElement firstmatch2 = driver.findElement(By.className("css-1p4va6y eudvd6x0"));
	String dollaramaItem = firstmatch2.getText();
	
	//search on costco website
	String search3 = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));
	driver.get(search3);
	WebElement firstmatch3 = driver.findElement(By.className("css-1p4va6y eudvd6x0"));
	String costcoItem = firstmatch3.getText();
}
}
