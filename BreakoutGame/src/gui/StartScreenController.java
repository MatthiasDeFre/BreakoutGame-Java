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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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
    private Button btnOpdrachtAanmaken;
    @FXML
    private Button btnOpdrachtVerwijderen;
    @FXML
    private Button btnOpdrachtDetails;
    @FXML
    private Button btnOpdrachtKopiëren;
    @FXML
    private Button btnOpdrachtAanpassen;

    //NONFXML Attributes
    SceneController controller = new SceneController();
    private Controller dc;
    private ObservableList<String> listExercices;

    public StartScreenController() {
        controller.screenInit("StartScreen.FXML");
//        listExercices = FXCollections.observableArrayList(dc.getListAllExercises());
//        listviewOef.setItems(listExercices);
    }

    @FXML
    private void btnOpdrachtAanmakenClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtVerwijderenClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtDetailsClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtKopiërenClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtAanpassenClick(MouseEvent event) {
    }
}
