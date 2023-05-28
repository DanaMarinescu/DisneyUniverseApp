package org.disneyWorld.sre.controllers;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.disneyWorld.sre.exceptions.StockUnavailable;
import org.disneyWorld.sre.model.Character;
import org.disneyWorld.sre.services.CharacterService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharactersController implements Initializable {
    private Stage window;
    private Scene scene;
    private Parent root;

    private static Character selected;
    @FXML
    private TableView<Character> charactersTable;

    @FXML
    private TableColumn<Character, String> id_name;
    @FXML
    private TableColumn<Character, String> id_age;
    @FXML
    private TableColumn<Character, Float> id_price;

    public void addToCart(javafx.event.ActionEvent actionEvent) throws IOException, StockUnavailable {
        selected=charactersTable.getSelectionModel().getSelectedItem();
        try{
            CharacterService.checkStock(selected.getStock());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
            window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (StockUnavailable e) {
            e.printStackTrace();
        }
    }

    public void toSuppliers(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("suppliers.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    public static Character getSelect(){
        return selected;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        CharacterService.initDatabase(SuppliersController.getSelected());
        id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id_age.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        charactersTable.setItems(CharacterService.getCharacters());
    }
}
