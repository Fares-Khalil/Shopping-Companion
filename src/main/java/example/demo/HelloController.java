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

import javax.xml.crypto.Data;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailAddress;

    @FXML
    private Button loggingIn;

    @FXML
    private PasswordField password_entry;

    @FXML
    private Button siginingUp;

    @FXML
    private Label wrongEmail;

    @FXML
    private Label wrongPassword;

    String email;
    String password;
    Boolean check = false;

    Database data = new Database();

    @FXML
    void checkCredentials(ActionEvent event) {
        email = emailAddress.getText();
        password = password_entry.getText();
        check = data.SignIn(email, password);
        if(check){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
            try{
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch(Exception e){

            }

        }else{
            wrongEmail.setText("Please check your email address");
            wrongPassword.setText("Please check your password");
            emailAddress.clear();
            password_entry.clear();

        }
    }

    @FXML
    void goToSignUp(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {

        }
    }
}