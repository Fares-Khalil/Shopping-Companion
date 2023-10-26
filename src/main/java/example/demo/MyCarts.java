package example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;

public class MyCarts {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> dollaCart;

    @FXML
    ListView<String> costCart;

    @FXML
    ListView<String> wholesCart;

    String item1, item2, item3;


    public Cart costcoC = new Cart("Costco");
    public Cart wholesaleC = new Cart("WholesaleClub");
    public Cart dollaramaC = new Cart("Dollarama");

    public void getCarts(Cart costcoCart_in, Cart wholesaleCart_in, Cart dollaramaCart_in, String item_doll, String item_cosc, String item_whole)
    {
        this.costcoC = costcoCart_in;
        this.wholesaleC = wholesaleCart_in;
        this.dollaramaC = dollaramaCart_in;
        this.item1 = item_doll;
        this.item2 = item_cosc;
        this.item3 = item_whole;
        showResults();
    }
    @FXML
    public void clearDollarama(ActionEvent a)
    {
        this.dollaramaC.removeall();
        dollaramaC.getKeys();
        showResults();
    }

    @FXML
    public void clearWholesale(ActionEvent a)
    {
        this.wholesaleC.removeall();
        wholesaleC.getKeys();
        showResults();
    }

    @FXML
    public void clearCostco(ActionEvent a)
    {
        this.costcoC.removeall();
        costcoC.getKeys();
        showResults();
    }

    @FXML
    public void removeWholeitem(ActionEvent a)
    {
        if(!this.wholesCart.getItems().isEmpty()) {
            String item = wholesCart.getSelectionModel().getSelectedItem().toString();
            String name = item.substring(0,item.indexOf(" Item Price: ")-1);
            wholesaleC.getKeys();
            Integer i = wholesaleC.getKey(name);
            this.wholesaleC.removeItem(wholesaleC.getKey(name));
        }
        showResults();
    }

    @FXML
    public void removeCostcoitem(ActionEvent a){
        if(!this.costCart.getItems().isEmpty()){
            String item = costCart.getSelectionModel().getSelectedItem().toString();
            String name = item.substring(0,item.indexOf(" Item Price: "));
            costcoC.getKeys();
            Integer i = costcoC.getKey(name);
            this.costcoC.removeItem(i);
        }
        showResults();

    }

    @FXML
    public void removeDollaramaitem(ActionEvent a){
        if(!this.dollaCart.getItems().isEmpty()) {
            String item = dollaCart.getSelectionModel().getSelectedItem().toString();
            String name = item.substring(0,item.indexOf(" Item Price: "));
            dollaramaC.getKeys();
            Integer i = dollaramaC.getKey(name);
            this.dollaramaC.removeItem(i);
        }
        showResults();
    }

    public void showResults(){
        dollaCart.getItems().clear();
        wholesCart.getItems().clear();
        costCart.getItems().clear();
        String[] items_costco = costcoC.displayItems();
        String[] items_doll = dollaramaC.displayItems();
        String[] items_whole = wholesaleC.displayItems();

        if(items_whole.length > 0){
            this.wholesCart.getItems().addAll(items_whole);
        }
        if(items_doll.length>0){
            this.dollaCart.getItems().addAll(items_doll);
        }
        if(items_costco.length>0){
            this.costCart.getItems().addAll(items_costco);

        }

    }

    @FXML
    public void goBack(ActionEvent event){
        try{
        //send carts to next scene lol
        FXMLLoader loader;
        if(item1 == "" && item2 == ""  && item3 == ""){
            loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        }
        else{
            loader = new FXMLLoader(getClass().getResource("SearchResults.fxml"));
        }

        //pooopoo pee pee
        root = loader.load();


        //change scene
        // root = FXMLLoader.load(getClass().getResource("myCarts.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if(!(item1 == "" && item2 == ""  && item3 == "")){
            SearchResults search = loader.getController();
            search.setCarts(this.costcoC,this.wholesaleC, this.dollaramaC);
            search.setName(item1,item2,item3);
        }

    }catch (Exception e){

    }

    }
}


