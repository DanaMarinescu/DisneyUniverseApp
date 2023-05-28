package org.disneyWorld.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.Character;
import org.disneyWorld.sre.services.CharacterService;
import org.disneyWorld.sre.exceptions.CharacterAlreadyExistsException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminAvailableCharactersController implements Initializable {
    @FXML
    private TextField addName;
    @FXML
    private TextField addAge;
    @FXML
    private TextField editCharacterName;
    @FXML
    private TextField editAgeCategory;
    @FXML
    private TextField addPrice;
    @FXML
    private TextField addStock;
    @FXML
    private TextField editPrice;
    @FXML
    private TextField editStock;
    @FXML
    private Text editMessage;
    @FXML
    private Text editMessage2;
    @FXML
    private TableView<Character> editTable;
    @FXML
    private TableView<Character> deleteTable;
    @FXML
    private TableColumn<Character,String> Name;
    @FXML
    private TableColumn<Character,String> Age;
    @FXML
    private TableColumn<Character, Integer> Price;
    @FXML
    private TableColumn<Character, Integer> Stock;
    @FXML
    private TableColumn<Character,String> deleteName;
    @FXML
    private TableColumn<Character,String> deleteAge;
    @FXML
    private TableColumn<Character, String> deletePrice;


    private Stage window;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Age.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        deleteName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deleteAge.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));
        deletePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        editTable.setItems(CharacterService.getCharacters());
        deleteTable.setItems(CharacterService.getCharacters());
        editTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                editCharacterName.setText(editTable.getSelectionModel().getSelectedItem().getName());
                editAgeCategory.setText(editTable.getSelectionModel().getSelectedItem().getAgeCategory());
                editPrice.setText(Float.toString(editTable.getSelectionModel().getSelectedItem().getPrice()));
                editStock.setText(Integer.toString(editTable.getSelectionModel().getSelectedItem().getStock()));
            }
        });
    }



    @FXML
    public void editCharacter(){
        if((editCharacterName.getText())!=null && (editAgeCategory.getText())!=null && Float.valueOf(editPrice.getText())!=0 && Integer.parseInt(editStock.getText())!=0){
            CharacterService.editCharacter(editTable.getSelectionModel().getSelectedItem(),editCharacterName.getText(),editAgeCategory.getText(),Float.valueOf(editPrice.getText()),Integer.parseInt(editStock.getText()));
            editTable.setItems(CharacterService.getCharacters());
            deleteTable.setItems(CharacterService.getCharacters());
        }
        else editMessage.setText("Complete all fields!");
    }
    @FXML
    public void addCharacter(){
        try {
            if(addName.getText()!=null && addAge.getText()!=null && Integer.parseInt(addStock.getText())!=0 && Float.valueOf(addPrice.getText())!=0) {
                CharacterService.addCharacter(addName.getText(), addAge.getText(), Float.valueOf(addPrice.getText()),Integer.parseInt(addStock.getText()));
                editTable.setItems(CharacterService.getCharacters());
                deleteTable.setItems(CharacterService.getCharacters());
            }
            else editMessage2.setText("Complete all fields!");
        }catch(CharacterAlreadyExistsException e){
            editMessage2.setText(e.getMessage());
        }
    }

    @FXML
    public void deleteCharacter(){
        CharacterService.deleteCharacter(deleteTable.getSelectionModel().getSelectedItem());
        editTable.setItems(CharacterService.getCharacters());
        deleteTable.setItems(CharacterService.getCharacters());
    }

    @FXML
    public void goToHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        scene=new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("adminHomePage.fxml")));
        window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
