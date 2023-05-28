package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientPastOrdersController implements Initializable {
    private Stage window;
    private Scene scene;
    private Parent root;
    private static Order selected;

    @FXML
    private TableColumn<Order, String> id_order;

    @FXML
    private TableColumn<Order, String> id_status;

    @FXML
    private TableView<Order> pastOrdersTable;
    @FXML
    private TextField id_name;
    @FXML
    private TextField id_s;
    @FXML
    private TextField id_price;
    @FXML
    void seeDetails() {
        selected =pastOrdersTable.getSelectionModel().getSelectedItem();
        String text = pastOrdersTable.getSelectionModel().getSelectedItem().getCharacterName();
        String text1 = Float.toString(pastOrdersTable.getSelectionModel().getSelectedItem().getPrice());
        String text2 = pastOrdersTable.getSelectionModel().getSelectedItem().getStatus();

        id_name.setText(text);
        id_price.setText(text1);
        id_s.setText(text2);
    }

    @FXML
    public void goToHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientHomePage.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SuppliersController.initializeSuppliers();
        id_order.setCellValueFactory(new PropertyValueFactory<>("characterName"));
        id_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        pastOrdersTable.setItems(OrderService.getClientOrders(LoginController.currentUser.getUsername()));
    }
}