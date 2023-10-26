package example.demo;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//import org.apache.jasper.tagplugins.jstl.core.Url;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SearchResults {
    public Cart costcoCart = new Cart("Costco");
    public Cart wholesaleCart = new Cart("WholesaleClub");
    public Cart dollaramaCart = new Cart("Dollarama");
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView dollaramaPic;
    @FXML
    ImageView costcoPic;
    @FXML
    ImageView wholesalePic;

    @FXML
    ListView<String> dollaramaProduct;

	@FXML
	ListView<String>  costcoProduct;

	@FXML
	ListView<String>  wholesaleclubProduct;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField quantity1;
    @FXML
    private TextField quantity2;
    @FXML
    private TextField quantity3;
    private int count1;
    private int count2;
    private int count3;

    String dollaramaPrice = " ";
    String costcoPrice = " ";
    String wholesalePrice = " ";

    onlineSearch[] search = new onlineSearch[3];

    @FXML
    public void viewmyCarts(ActionEvent a){
        try {
            //send carts to next scene lol
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myCarts.fxml"));
            //pooopoo pee pee
            root = loader.load();
            MyCarts myCartsController = loader.getController();
            /*myCartsController.costcoC = this.costcoCart;
            myCartsController.wholesaleC = this.wholesaleCart;
            myCartsController.dollaramaC = this.dollaramaCart;
            */

            //change scene
            // root = FXMLLoader.load(getClass().getResource("myCarts.fxml"));
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            myCartsController.getCarts(this.costcoCart,this.wholesaleCart, this.dollaramaCart,dollaramaPrice,costcoPrice,wholesalePrice);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void setName(String input1, String input2, String input3) {
        dollaramaPrice = input1;
        costcoPrice = input2;
        wholesalePrice = input3;
        getResults();
    }


    public void setCarts(Cart costcoCart_in, Cart wholesaleCart_in, Cart dollaramaCart_in)
    {
        this.costcoCart = costcoCart_in;
        this.wholesaleCart = wholesaleCart_in;
        this.dollaramaCart = dollaramaCart_in;
    }

    public void getResults() {
        dollaramaProduct.getItems().add(dollaramaPrice);
        costcoProduct.getItems().add(costcoPrice);
        wholesaleclubProduct.getItems().add(wholesalePrice);
        String url_doll = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\dollarama.png";
        String url_cosc = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\costco.png";
        String url_whole = System.getProperty("user.dir")+"\\src\\main\\resources\\example\\demo\\wholesaleclub.png";
    try{
        BufferedImage image_doll =ImageIO.read(new File(url_doll));
        BufferedImage image_cosc =ImageIO.read(new File(url_cosc));
        BufferedImage image_whole =ImageIO.read(new File(url_whole));
        dollaramaPic.setImage(SwingFXUtils.toFXImage(image_doll,null));
        costcoPic.setImage(SwingFXUtils.toFXImage(image_cosc,null));
        wholesalePic.setImage(SwingFXUtils.toFXImage(image_whole,null));
    }catch (Exception e){

        }

    }

    @FXML
    public void CostcoAdd(ActionEvent a) {
        String[] costco = costcoPrice.split("\nPrice: \\$");
        if (count2 != 0) {
            costcoCart.addItem(costco[0], costco[1], Integer.toString(count2));
            costcoCart.displayItems();
        }

    }
    @FXML
    public void WholesaleAdd(ActionEvent a) {
        String[] wholeSale = wholesalePrice.split("\nPrice: \\$");
        if (count3 != 0) {
            wholesaleCart.addItem(wholeSale[0], wholeSale[1], Integer.toString(count3));
            wholesaleCart.displayItems();
        }

    }
    @FXML
    public void DollaramaAdd(ActionEvent a) {
        if (count1 != 0) {
            String[] dollarama = dollaramaPrice.split("\nPrice: \\$");
            dollaramaCart.addItem(dollarama[0], dollarama[1], Integer.toString(count1));
            dollaramaCart.displayItems();
        }
    }


    @FXML
    public void ButtonAdd1(ActionEvent a) {
        count1+= 1;
        quantity1.setText(Integer.toString(count1));
    }

    @FXML
    public void ButtonAdd2(ActionEvent a) {
        count2+= 1;
        quantity2.setText(Integer.toString(count2));
    }
    @FXML
    public void ButtonAdd3(ActionEvent a) {
        count3+= 1;
        quantity3.setText(Integer.toString(count3));
    }

    @FXML
    public void ButtonMinus1(ActionEvent a) {
        if (count1 == 0)
        {
            count1 = 0;
        }
        else {
            count1-= 1;
        }
        quantity1.setText(Integer.toString(count1));
    }

    @FXML
    public void ButtonMinus2(ActionEvent a) {
        if (count2 == 0)
        {
            count2 = 0;
        }
        else {
            count2-= 1;}
        quantity2.setText(Integer.toString(count2));
    }
    @FXML
    public void ButtonMinus3(ActionEvent a) {
        if (count3 == 0)
        {
            count3 = 0;
        }
        else{
            count3-= 1;}
        quantity3.setText(Integer.toString(count3));
    }
    //Nick was here
    @FXML
    public void searchAction(ActionEvent event) throws IOException {

        //initialize stores
        search[0] = new Dollarama();
        search[1] = new Costco();
        search[2] = new WholesaleClub();
        //search and store for each store
        String[] dollarama, costco, wholesaleclub;
        dollarama = search[0].searchItem(searchBar.getText());
        costco = search[1].searchItem(searchBar.getText());
        wholesaleclub = search[2].searchItem(searchBar.getText());

        //Inforamtion into one string
        String dollaramaInfo, costcoInfo, wholesaleInfo;

        dollaramaInfo = dollarama[0] + "\nPrice: $" + dollarama[1];
        costcoInfo = costco[0] + "\nPrice: $" + costco[1];
        wholesaleInfo = wholesaleclub[0] + "\nPrice: $" + wholesaleclub[1];

        //send search results to next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchResults.fxml"));

        root = loader.load();
        SearchResults searchResultsController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("SearchResultsnew.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        searchResultsController.setName(dollaramaInfo, costcoInfo, wholesaleInfo);
        searchResultsController.setCarts(costcoCart,wholesaleCart,dollaramaCart);
    }
}

