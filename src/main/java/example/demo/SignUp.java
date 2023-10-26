package example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUp {
    private Stage stage;
    private Scene scene;
    private Parent root;
    String fName = "";
    String lName = "";
    String email = "";
    String password = "";
    Database data = new Database();

    @FXML
    private Label errorMsg;

    @FXML
    private TextField Email;

    @FXML
    private TextField First;

    @FXML
    private TextField Last;

    @FXML
    private PasswordField Password;

    @FXML
    private Button back;

    @FXML
    private Button signUp;

    @FXML
    void addInformation(ActionEvent event) {
        fName = First.getText();
        lName = Last.getText();
        email = Email.getText();
        password = Password.getText();
        if(data.SignUp(fName,lName,email,password)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            try{
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){

            }
        }
        else{
            errorMsg.setText("Already have an account. Please go back");
        }


    }

    @FXML
    void goToLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    try{
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }catch (Exception e){

    }

    }

}
