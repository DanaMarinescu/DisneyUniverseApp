package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AdminHomePageController implements Initializable {
    @FXML
    private Button AvailableCharacters;
    @FXML
    private Button CurrentOrders;
    @FXML
    private Button PastOrders;
    @FXML
    private Button LogOut;

    private Stage window;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void viewAvailableCharacters(javafx.event.ActionEvent actionEvent) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("availableDisneyCharacters.fxml")));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void viewCurrentOrders(javafx.event.ActionEvent actionEvent) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminCurrentOrders.fxml")));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void viewPastOrders(javafx.event.ActionEvent actionEvent) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminPastOrders.fxml")));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void goToLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml")));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}

