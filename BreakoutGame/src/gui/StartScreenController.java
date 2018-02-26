/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.UseCaseExerciseAdminController;
import domain.Exercise;
import domain.SceneName;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML UseCaseExerciseAdminController class
 *
 * @author Alexander
 */
public class StartScreenController extends GridPane implements Observer {

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

    @FXML
    private ListView<Exercise> lstTest;
    @FXML
    private TableView<Exercise> tblExercises;
    @FXML
    private TableColumn<Exercise, String> clmAssignment;
    @FXML
    private TableColumn<Exercise, String> clmDescription;

    //NONFXML Attributes
    private UseCaseExerciseAdminController dc;
    private ObservableList<Exercise> listExercices;
    private SceneController2 sceneController2;
    @FXML
    private Label lblExec;

    public StartScreenController(UseCaseExerciseAdminController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }

        this.dc = dc;
//        dc.addObserver(this);
        listExercices = FXCollections.observableArrayList(dc.getListAllExercisesE());
        lstTest.setItems(listExercices);
        tblExercises.setItems(listExercices);
        clmAssignment.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategoryDescription()));
        clmDescription.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getAssignment()));
    }

    @FXML
    private void btnOpdrachtAanmakenClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtVerwijderenClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtDetailsClick(MouseEvent event) {
//        SceneController2.createScene(SceneName.EXERCISESCREEN);
        dc.setExercise(tblExercises.getSelectionModel().getSelectedItem());
//        SceneController2.switchScene(SceneName.EXERCISESCREEN);
    }

    @FXML
    private void btnOpdrachtKopiërenClick(MouseEvent event) {
    }

    @FXML
    private void btnOpdrachtAanpassenClick(MouseEvent event) {

    }

    @Override
    public void update(Observable o, Object obj) {
        Exercise exercise = (Exercise) obj;
        lblExec.setText(exercise.getName());
    }
}
