package org.disneyWorld.sre.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.disneyWorld.sre.model.ModelPH;
import org.disneyWorld.sre.services.OrderService;
import org.disneyWorld.sre.services.SupplierService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuppliersController implements Initializable {
    private Stage window;
    private Scene scene;
    private Parent root;

    private static String selected;

    @FXML
    private TableColumn<ModelPH, String> col_name;

    @FXML
    private TableView<ModelPH> table;

    public void toDisneyCharacters(javafx.event.ActionEvent actionEvent) throws IOException {
        selected=table.getSelectionModel().getSelectedItem().getSupplierName();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("characters.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_name.setCellValueFactory(new PropertyValueFactory<ModelPH, String>("supplierName"));
        initializeSuppliers();
        table.setItems(SupplierService.getSuppliers());
    }

    public static void initializeSuppliers(){
        ObservableList<ModelPH> suppliers=SupplierService.getSuppliers();
        for (ModelPH ph:suppliers){
            if(OrderService.isClosed())
                OrderService.initDatabase(ph.getSupplierName());
        }
    }

    public static String getSelected(){
        return selected;
    }


    public void toHomePage(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientHomePage.fxml"));
        window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
