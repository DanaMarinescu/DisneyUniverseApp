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


public class ClientHomePageController {
     @FXML
     private Button logOut;
     @FXML
     private Button pastOrders;
     @FXML
     private Button cart;
     @FXML
     private Button disneyPrincesses;
     @FXML
     private Button classicDisneyCharacters;
     @FXML
     private Button hobbitCharacters;
     private Stage window;
     private Scene scene;
     private Parent root;
    private static String selectedCategoryName;

    @FXML
     public void handleLogOut(ActionEvent event) throws IOException {

         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
         window= (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         window.setScene(scene);
         window.show();
     }
    @FXML
     public void handlePastOrders(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminPastOrders.fxml"));
         window= (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         window.setScene(scene);
         window.show();
     }
    @FXML
     public void handleCart(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
         window= (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         window.setScene(scene);
         window.show();
    }

    @FXML
     public void handleDisneyPrincesses(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DisneyPrincesses.fxml"));
         window= (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         window.setScene(scene);
         window.show();
     }
    @FXML
     public void handleClassicDisneyCharacters(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ClassicDisneyCharacters.fxml"));
         window= (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         window.setScene(scene);
         window.show();
    }
    @FXML
      public void handleHobbitCharacters(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("TheHobbitCharacters.fxml"));
         window= (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         window.setScene(scene);
         window.show();
    }
    @FXML
    public static String getSelectedCategoryName() {
        return selectedCategoryName;
    }


}