package example.demo;

import javafx.scene.image.Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

interface onlineSearch {
    public String[] searchItem(String Item);
}
//To be implemented in the future after finding workaround for Captcha
class Walmart implements onlineSearch{
    String walmartItem;
    String walmartPrice;
    String search;
    Cart[] walmartCarts = {};

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
    String urls;
    Database database;
    public Cart[] dollaramaCarts = {};
    Dollarama(){
        search = " ";
        database = new Database();
    }
    public String[] searchItem(String Item){
        String[] result_withURL = {"","",""};

        BufferedImage image =null;
        String[] result = {"",""};
        //search on dollarama website
        search = String.format("https://www.dollarama.com/en-CA/Search?keywords=%s",Item.replace(' ', '+'));
        try {
            Document document  = Jsoup.connect(search).get();
            Element link = document.select(".product-tile-price").first();

            dollaramaPrice = link.html().substring(link.html().indexOf('$'),link.html().indexOf("</span>"));
            link = document.select(".js-display-name").first();

            dollaramaItem = link.html().replace("<br>", " ");

            link = document.select("div[class=search-results]").first();
            Element src = link.select("img[src]").first();

            urls = src.attr("src");

            //dollaramaItem = driver.findElement(By.className("js-display-name")).getText();
            //dollaramaPrice = driver.findElement(By.className("product-tile-price")).getText();
        }
        catch(Exception e){
            dollaramaItem = "Item not found";
            dollaramaPrice = "";


        }

        result_withURL = database.checkDatabase(dollaramaItem, "Dollarama",Item,(dollaramaPrice.length()>0)?dollaramaPrice.substring(1):dollaramaPrice, urls);
        result[0] = result_withURL[0];
        result[1] = result_withURL[1];
        urls = result_withURL[2];
        String url_doll = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\dollarama.png";
        String url_notfound = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\notfound.png";


        try{
            if(result[0].equals("Item not found")){
                // read the url
                image = ImageIO.read(new File(url_notfound));

                ImageIO.write(image, "png",new File(url_doll));
            }
            else{
                URL url =new URL(urls);
                // read the url
                image = ImageIO.read(url);

                ImageIO.write(image, "png",new File(url_doll));

            }
        }catch (Exception e){

        }


        //result[0] = dollaramaItem;
        //result[1] =  dollaramaPrice;
        return result;
    }
}


class Costco implements onlineSearch {
    String costcoItem;
    String costcoPrice;
    String search;
    String urls;
    Database database;
    public Cart[] costcoCarts = {};
    Costco(){
        search = " ";
        database = new Database();

    }
    public String[] searchItem(String Item) {
        String[] result_withURL = {"","",""};

        String[] result = {"",""};

        BufferedImage image =null;

        //search on costco website
        search = String.format("https://www.costco.ca/CatalogSearch?dept=All&keyword=%s",Item.replace(' ', '+'));
        try {
            Document document  = Jsoup.connect(search).get();
            Element link = document.select(".price").first();

            costcoPrice = link.html();
            link = document.select(".description").first();

            costcoItem = link.html().substring( link.html().indexOf(">")+1,link.html().indexOf("</a>"));

            link = document.select("div[class=product-tile-set]").first();
            Element src = link.select("img[src]").first();

            urls = src.attr("src");

        }
        catch(Exception e)
        {
            costcoPrice = "";
            costcoItem = "Item not found";
        }
        result_withURL = database.checkDatabase(costcoItem, "Costco",Item,(costcoPrice.length()>0)?costcoPrice.substring(1):costcoPrice,urls);

        result[0] = result_withURL[0];
        result[1] = result_withURL[1];
        urls = result_withURL[2];
        String url_notfound = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\notfound.png";
        String url_cosc = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\costco.png";
        try{
            if(result[0].equals("Item not found")){
                // read the url
                image = ImageIO.read(new File(url_notfound));

                ImageIO.write(image, "png",new File(url_cosc));
            }
            else{
                URL url =new URL(urls);
                // read the url
                image = ImageIO.read(url);

                ImageIO.write(image, "png",new File(url_cosc));

            }
        }catch (Exception e){

        }


        //result[0] = costcoItem;
        //result[1] =  costcoPrice;
        return result;
    }
}

class WholesaleClub implements onlineSearch{
    String wholesaleClubPrice;
    String wholesaleClubItem;
    String search;
    String urls;
    Database database;
    WholesaleClub(){
        search = " ";
        database = new Database();

    }
    public String[] searchItem(String Item) {
        String[] result_withURL = {"","",""};
        String[] result = {"",""};
        BufferedImage image =null;

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("headless");

        WebDriver driver = new ChromeDriver(opt);
        //search on wholesaleClub website
        search = String.format("https://www.wholesaleclub.ca/search?search-bar=%s",Item.replace(" ", "%20"));

        try {
            driver.get(search);
            while(!driver.getTitle().contains("Search Results")) {
            }
            Thread.sleep(10);
            wholesaleClubPrice = driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[3]/div[1]/div/div[3]/div/div/span/span[1]")).getText();
            wholesaleClubItem = driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[3]/div[1]/h3/a/span/span[2]")).getText() + driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[3]/div[1]/h3/a/span/span[3]")).getText();
            urls = driver.findElement(By.xpath("//*[@id=\"odd\"]/div/div/div[2]/div/img")).getAttribute("src");

        }
        catch(Exception e){
            wholesaleClubPrice = "";
            wholesaleClubItem = "Item not found";
        }


        result_withURL = database.checkDatabase(wholesaleClubItem, "Wholsesale Club",Item,(wholesaleClubPrice.length()>0)?wholesaleClubPrice.substring(1):wholesaleClubPrice,urls);

        result[0] = result_withURL[0];
        result[1] = result_withURL[1];
        urls = result_withURL[2];
        String url_notfound = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\notfound.png";
        String url_whole = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\wholesaleclub.png";

        try{
            if(result[0].equals("Item not found")){
                //URL url =new URL("D:/My Studies/College Courses/Term 5/ECE 5010/demo/src/main/resources/example/demo/notfound.png");
                // read the url
                image = ImageIO.read(new File(url_notfound));

                ImageIO.write(image, "png",new File(url_whole));
            }
            else{
                URL url =new URL(urls);
                // read the url
                image = ImageIO.read(url);

                ImageIO.write(image, "png",new File(url_whole));

            }
        }catch (Exception e){

        }


        //result[0] = wholesaleClubItem;
        //result[1] =  wholesaleClubPrice;
        return result;
    }

}
