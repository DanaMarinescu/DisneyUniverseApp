package org.disneyWorld.sre.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;


public class ClientHomePageController{
    @FXML
    private Button logOut;
    @FXML
    private Button pastOrders;
    @FXML
    private Button suppliers;

    private Stage window;
    private Scene scene;
    private Parent root;


    public void handleLogOut(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        window= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
    @FXML
    public void handlePastOrders(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientPastOrders.fxml"));
        window= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleSuppliers(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("suppliers.fxml")));
        window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}