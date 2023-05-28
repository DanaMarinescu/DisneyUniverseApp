package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.disneyWorld.sre.exceptions.UsernameAlreadyExistsException;
import org.disneyWorld.sre.services.SupplierService;
import org.disneyWorld.sre.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    private Stage window;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    public void handleRegisterAction(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            SupplierService.addUser(usernameField.getText(),(String) role.getValue());
            registrationMessage.setText("Account created successfully!");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            window= (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
    public void toLogin(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
