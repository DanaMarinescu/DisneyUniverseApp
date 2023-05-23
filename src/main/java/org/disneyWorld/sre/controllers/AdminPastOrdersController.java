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
import org.disneyWorld.sre.model.DisneyCharacter;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPastOrdersController implements Initializable {
    @FXML
    private TableView<Order> pastOrders;
    @FXML
    private TableColumn<DisneyCharacter, String> ClientNAME;
    @FXML
    private TableColumn<DisneyCharacter, String> characterName;

    @FXML
    private TableColumn<DisneyCharacter, String> characterAge;

    @FXML
    private TableColumn<DisneyCharacter, String> price;

    @FXML
    private TableColumn<Order, String> status;

    private Scene scene;
    private Stage window;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ClientNAME.setCellValueFactory(new PropertyValueFactory<>("client name"));
        characterName.setCellValueFactory(new PropertyValueFactory<>("character name"));
        characterAge.setCellValueFactory(new PropertyValueFactory<>("character age"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        pastOrders.setItems(OrderService.getPastOrders());
    }


    public void goBack(javafx.event.ActionEvent actionEvent) throws IOException {
        scene=new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminHomePage.fxml")));
        window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}