package org.disneyWorld.sre.controllers;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import org.disneyWorld.sre.model.DisneyCharacter;
import javafx.fxml.Initializable;
import org.disneyWorld.sre.services.DisneyCharacterService;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import org.disneyWorld.sre.exceptions.StockUnavailable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

  public class DisneyCharactersController implements Initializable
 {
      private Stage window;
      private Scene scene;
      private Parent root;
      private static DisneyCharacter select;
      private TableView<DisneyCharacter> charactersTable;
      @FXML
      private Button id_cart;
      @FXML
      private TableColumn<DisneyCharacter, String> id_name;
      @FXML
      private TableColumn<DisneyCharacter, Float> id_price;
      public void addToCart (javafx.event.ActionEvent actionEvent) throws IOException, StockUnavailable {
          select=charactersTable.getSelectionModel().getSelectedItem();
          try{
              DisneyCharacterService.checkStock(select.getStock());
              Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
              window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
              scene = new Scene(root);
              window.setScene(scene);
              window.show();
         }  catch (StockUnavailable e) {
              e.printStackTrace();
         }
    }
      public void toHomePage(javafx.event.ActionEvent actionEvent) throws IOException {

          Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientHomePage.fxml"));
          window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
          scene = new Scene(root);
          window.setScene(scene);
          window.show();
     }
      public void toCart(javafx.event.ActionEvent actionEvent) throws IOException {

          Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
          window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
          scene = new Scene(root);
          window.setScene(scene);
          window.show();
     }
    private boolean isSameCategory(String categoryName) {
        String selectedCategory = ClientHomePageController.getSelectedCategoryName();
        return categoryName.equals(selectedCategory);
    }
    public static DisneyCharacter getSelect(){ return select;}
     public void initialize( URL url, ResourceBundle resourceBundle) {
        // DisneyCharacterService.initDatabase(CharacterCategoriesController.getSelectat());
         id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
         id_price.setCellValueFactory(new PropertyValueFactory<>("price"));
         charactersTable.setItems(DisneyCharacterService.getCharacters());
     }
 }
//java -jar --module-path "C:\devSources\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml "C:\Users\Doinita\Downloads\nitrite-explorer-3.4.3.jar"