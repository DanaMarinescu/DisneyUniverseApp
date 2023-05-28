package org.disneyWorld.sre.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.disneyWorld.sre.exceptions.UserDoesNotExistException;
import org.disneyWorld.sre.model.Character;
import org.disneyWorld.sre.model.User;
import org.disneyWorld.sre.services.CharacterService;
import org.disneyWorld.sre.services.OrderService;
import org.disneyWorld.sre.services.UserService;
import java.io.IOException;

public class LoginController {
    private Stage window;
    private Scene scene;

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    public static User currentUser;

    public void handleLogInAction(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            if(UserService.checkUser(usernameField.getText(), passwordField.getText()).equals("Client")){
                currentUser=new User(usernameField.getText(),passwordField.getText(),"Client");
                Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("clientHomePage.fxml"));
                window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                scene=new Scene(root);
                window.setScene(scene);
                window.show();
            }
            else{
                Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("adminHomePage.fxml"));
                window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                scene=new Scene(root);
                window.setScene(scene);
                window.show();
                CharacterService.initDatabase(usernameField.getText());
                OrderService.initDatabase(usernameField.getText());
            }

        } catch (UserDoesNotExistException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML

    public void goToRegister(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        window= (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
