/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.effects.JFXDepthManager;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExercisesPaneLeftController extends AnchorPane {

    @FXML
    private TableView<Exercise> tblExercises;
    @FXML
    private TableColumn<Exercise, String> clmSection;
    @FXML
    private TableColumn<Exercise, String> clmDescription;
    @FXML
    private JFXButton btnCreateExercise;
    @FXML
    private JFXButton btnCopyExercise;
    @FXML
    private JFXButton btnDeleteExercise;

    //NONFXML Attributes
    private ExerciseDomainController dc;
    private ObservableList<Exercise> listExercices;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXColorPicker colorPicker;

    public ExercisesPaneLeftController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExercisesPaneLeft.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }

        this.dc = dc;
//        dc.addObserver(this);
        listExercices = dc.getListAllExercisesE();
        tblExercises.setItems(listExercices);
        clmSection.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmDescription.setCellValueFactory(e -> e.getValue().assignmentProperty());
        tblExercises.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setExerciseToDc();
            }
        });
        
        JFXDepthManager.setDepth(tblExercises, 3);
        //gggg
    }

    @FXML
    private void btnCreateExerciseOnClick(MouseEvent event) {
        dc.setManagerMode(PersistMode.NEW);
        dc.setExercise(new Exercise());
    }

    @FXML
    private void btnCopyExerciseOnClick(MouseEvent event) {
    }

    @FXML
    private void btnDeleteExerciseOnClick(MouseEvent event) {
    }

    private void setExerciseToDc() {
        dc.setManagerMode(PersistMode.UPDATE);
        dc.setExercise(tblExercises.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void colorPickerOnAction(ActionEvent event) {
        Color selectedColor =  colorPicker.getValue();
        tblExercises.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
