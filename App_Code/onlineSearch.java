package App_Code;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
/*Focuses on online searching*/

public class onlineSearch {
private static void searchItem(String Item){
	WebDriver driver = new ChromeDriver();
	
	//search on walmart website
	String search = String.format("https://www.walmart.ca/search?q=%s",Item.replace(' ', '+'));
	driver.get(search);
	WebElement firstmatch = driver.findElement(By.className("css-1p4va6y eudvd6x0"));
	String walmartItem = firstmatch.getText();
	
	//search on sobeys website
	
}
}
