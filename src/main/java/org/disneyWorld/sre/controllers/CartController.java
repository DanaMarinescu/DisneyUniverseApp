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
import org.disneyWorld.sre.services.OrderService;
import org.disneyWorld.sre.model.Character;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    private Stage window;
    private Scene scene;
    private Parent root;
    private static Character selected;

    @FXML
    private TableView<Character> cartTable;
    @FXML
    private TableColumn<Character,String> id_name;
    @FXML
    private TableColumn<Character,Float> id_price;
    @FXML
    private TableColumn<Character, String> id_age;
    @FXML
    private TextField id_message;

    @FXML
    void toHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientHomePage.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void toOrder(){
        selected=cartTable.getSelectionModel().getSelectedItem();
        OrderService.addOrder(LoginController.currentUser.getUsername(),selected,"Placed");
        id_message.setText("PLACED!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id_age.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTable.getItems().add(CharactersController.getSelect());
    }
}
