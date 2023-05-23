package org.disneyWorld.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.services.OrderService;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCurrentOrdersController implements Initializable {
    @FXML
    private TableView<Order> currentOrders;
    @FXML
    private TableColumn<Order, String> ClientNAME;
    @FXML
    private TableColumn<Order, String> characterNAME;
    @FXML
    private TableColumn<Order, String> characterAGE;
    @FXML
    private TableColumn<Order, String> price;
    @FXML
    private TableColumn<Order, String> status;
    @FXML
    private TableColumn<Order, Void> acceptRejectColumn;
    @FXML
    private TextField orderAccept;

    private static String user;
    private Scene scene;
    private Stage window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientNAME.setCellValueFactory(new PropertyValueFactory<>("ClientNAME"));
        characterNAME.setCellValueFactory(new PropertyValueFactory<>("characterNAME"));
        characterAGE.setCellValueFactory(new PropertyValueFactory<>("characterAGE"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        acceptRejectColumn.setCellFactory(param -> new TableCell<>() {
            private final Button acceptButton = createButton("Accept", Color.GREEN);
            private final Button rejectButton = createButton("Reject", Color.RED);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Order order = getTableView().getItems().get(getIndex());
                    HBox buttonsContainer = new HBox(acceptButton, rejectButton);
                    buttonsContainer.setSpacing(5);
                    setGraphic(buttonsContainer);

                    acceptButton.setOnAction(event -> handleAcceptButton(order));
                    rejectButton.setOnAction(event -> handleRejectButton(order));
                }
            }

            private Button createButton(String text, Color color) {
                Button button = new Button(text);
                button.setTextFill(color);
                return button;
            }
        });

        currentOrders.setItems(OrderService.getCurrentOrders());
    }

    private void handleAcceptButton(Order order) {

        System.out.println("Accept button clicked for order: " + order);
    }

    private void handleRejectButton(Order order) {

        System.out.println("Reject button clicked for order: " + order);
    }
}
