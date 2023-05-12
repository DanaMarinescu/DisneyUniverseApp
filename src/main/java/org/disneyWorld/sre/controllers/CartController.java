package org.disneyWorld.sre.controllers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import org.disneyWorld.sre.model.DisneyCharacter;
import org.disneyWorld.sre.model.Order;
import org.disneyWorld.sre.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    private Stage window;
    private Scene scene;
    private Parent root;
    private static Character selectat;

    @FXML
    private TableView<Character> table;

    @FXML
    private TableColumn<Character,String> id_name;

    @FXML
    private TableColumn<Character,Float> id_age;

    @FXML
    private TableColumn<Character, String> id_price;

    @FXML
    private TextField id_ms;

    @FXML
    void toHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void toOrder(){
        selectat=table.getSelectionModel().getSelectedItem();
        OrderService.addOrder(LoginController.currentUser.getUsername(),selectat,"Placed");
        id_ms.setText("PLACED!");
    }

    public static Character getSelectat(){
        return selectat;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getItems().add(DisneyCharactersController.getSelect());
    }
}

