/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Controller;
import domain.Exercise;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class StartScreenController extends GridPane {

    @FXML
    private GridPane grid;
    @FXML
    private ListView<String> listviewOef;

    private Controller dc;
    private ObservableList<String> listExercices;

    public StartScreenController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        listExercices = FXCollections.observableArrayList(dc.getListAllExercises());
        listviewOef.setItems(listExercices);
    }
}
