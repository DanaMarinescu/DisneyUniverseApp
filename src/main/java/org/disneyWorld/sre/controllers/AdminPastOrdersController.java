package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPastOrdersController implements Initializable {
    @FXML
    private TableView<Order> pastOrders;
    @FXML
    private TableColumn<Order, String> characterName;
    @FXML
    private TableColumn<Order, String> ageCategory;
    @FXML
    private TableColumn<Order, Integer> price;
    @FXML
    private TableColumn<Order, String> status;
    @FXML
    private TableColumn<Order,String>username;

    private Scene scene;
    private Stage window;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        username.setCellValueFactory(new PropertyValueFactory<>("user"));
        characterName.setCellValueFactory(new PropertyValueFactory<>("characterName"));
        ageCategory.setCellValueFactory(new PropertyValueFactory<>("ageCategoryName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        pastOrders.setItems(OrderService.getPastOrders());
    }


    public void toHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        scene=new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminHomePage.fxml")));
        window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}