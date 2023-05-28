package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.model.Character;
import org.disneyWorld.sre.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCurrentOrdersController implements Initializable {
    @FXML
    private TableView<Order> currentOrders;
    @FXML
    private TableColumn<Character, String> CharacterName;
    @FXML
    private TableColumn<Character, String> CharacterAge;
    @FXML
    private TableColumn<Character, Integer> Price;
    @FXML
    private TableColumn<Order, String> Status;
    @FXML
    private TableColumn<Order,Integer>Stock;
    @FXML
    private TableColumn<Order,String>username;
    @FXML
    private TextField orderAccept;

    private static String user;
    private Scene scene;
    private Stage window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CharacterName.setCellValueFactory(new PropertyValueFactory<>("characterName"));
        CharacterAge.setCellValueFactory(new PropertyValueFactory<>("ageCategoryName"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        username.setCellValueFactory(new PropertyValueFactory<>("user"));
        currentOrders.setItems(OrderService.getCurrentOrders());
    }

    public void acceptOrder(javafx.event.ActionEvent actionEvent) throws IOException{
        if(currentOrders.getSelectionModel().getSelectedItem().getStock()>=1) {
            user=currentOrders.getSelectionModel().getSelectedItem().getUser();
            OrderService.editStatus(currentOrders.getSelectionModel().getSelectedItem(), "Accepted");
            scene=new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("applyDiscount.fxml")));
            window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            currentOrders.setItems(OrderService.getCurrentOrders());
        }else{
            orderAccept.setText("Insufficient stock");
            rejectOrder();
        }

    }


    public static String getUser(){
        return user;
    }

    public void rejectOrder(){
        OrderService.editStatus(currentOrders.getSelectionModel().getSelectedItem(),"Rejected");
        currentOrders.setItems(OrderService.getCurrentOrders());
    }

    public void goToHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        scene=new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminHomePage.fxml")));
        window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}