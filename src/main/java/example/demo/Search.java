package example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import javax.xml.crypto.Data;
import java.io.IOException;

public class Search {
    private Stage stage;
    private Scene scene;
    private Parent root;



    private String[] history;

    private String[] suggestions;

    private Database database = new Database();

    @FXML
    TextField searchBar;

    @FXML
    ListView<String> searchHistory;

    @FXML
    ListView<String> searchSuggestions;

    @FXML
    Label label1;

    @FXML
    Label label2;

    @FXML
    public void viewmyCarts(ActionEvent a){
        try {
            //send carts to next scene AABIR WAS HERE MUAHAHAHAHAH
            //pooopoo pee pee
            //change scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myCarts.fxml"));
            root = loader.load();
            MyCarts cartsController = loader.getController();
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            cartsController.item1 = "";
            cartsController.item2= "";
            cartsController.item3 = "";

        }catch (Exception e){

        }

    }


    @FXML
    public void searchAction(ActionEvent event) throws IOException {


        //initialize stores
        onlineSearch[] search = new onlineSearch[3];
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
    }
    @FXML
    void searchHistory(MouseEvent event) {
        searchHistory.getItems().clear();
        searchSuggestions.getItems().clear();
        label1.setText("History");
        searchHistory.getItems().clear();
        history = database.printSearchHistory();
        label2.setText("Suggestions");
        suggestions = database.displaySuggestion().toArray(new String[0]);
        searchHistory.getItems().addAll(history);
        searchSuggestions.getItems().addAll(suggestions);
    }
    @FXML
    void selection(MouseEvent event) {
        String data = searchHistory.getSelectionModel().getSelectedItem();
        searchBar.setText(data);
    }

    @FXML
    void selectionTwo(MouseEvent event) {
        String data = searchSuggestions.getSelectionModel().getSelectedItem();
        searchBar.setText(data);
    }

    @FXML
    public void initialize(){
        label1.setText("Suggestions");
        suggestions = database.displaySuggestion().toArray(new String[0]);
        searchHistory.getItems().addAll(suggestions);
    }

}
