package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplyDiscountController implements Initializable {
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order,String> name;
    @FXML
    private TableColumn<Order,String> age;
    @FXML
    private TableColumn<Order, Integer> price;
    @FXML
    private TableColumn<Order, String >status;
    @FXML
    private TableColumn<Order,String> username;
    @FXML
    private ChoiceBox amount;

    private Scene scene;
    private Stage window;

    public void initialize(URL url, ResourceBundle resourceBundle){
        amount.getItems().addAll("0","15","20","25");
        name.setCellValueFactory(new PropertyValueFactory<>("characterName"));
        age.setCellValueFactory(new PropertyValueFactory<>("ageCategoryName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        username.setCellValueFactory(new PropertyValueFactory<>("user"));
        table.setItems(OrderService.getClientOrders(AdminCurrentOrdersController.getUser()));
    }

    @FXML
    public void applyDiscount(javafx.event.ActionEvent actionEvent)throws IOException {
        if(table.getSelectionModel().getSelectedItem().getStatus().equals("Accepted")){
            table.getSelectionModel().getSelectedItem().setPrice(table.getSelectionModel().getSelectedItem().getPrice()-Integer.parseInt(amount.getValue().toString()));
            deliverOrder();
            goBack(actionEvent);
        }
    }

    private void deliverOrder(){
        OrderService.editStatus(table.getSelectionModel().getSelectedItem(),"Delivered");
    }

    @FXML
    public void goBack(javafx.event.ActionEvent actionEvent) throws IOException {
        scene=new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminHomePage.fxml")));
        window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
