/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.UseCaseExerciseAdminController;
import domain.Exercise;
import domain.GroupOperation;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML UseCaseExerciseAdminController class
 *
 * @author Alexander
 */
public class ExerciseDetailScreenController2 extends GridPane implements Observer {

    @FXML
    private Label lblExercise;

    private UseCaseExerciseAdminController dc;
    @FXML
    private AnchorPane grid;
    @FXML
    private Label lblAnswer;
    @FXML
    private Label lblFeedback;
    @FXML
    private Label lblAssignment;
    @FXML
    private Label lblCategory;
    @FXML
    private TableView<GroupOperation> tblViewGroupOperations;
    @FXML
    private TextField txtEx;
    @FXML
    private TextField txtAnw;
    @FXML
    private TextField txtFeedback;
    @FXML
    private TextField txtAssignment;
    @FXML
    private TextField txtCat;
    @FXML
    private TableColumn<GroupOperation, String> clmCat;
    @FXML
    private TableColumn<GroupOperation, String> clmValue;

    public ExerciseDetailScreenController2(UseCaseExerciseAdminController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExerciseDetailScreen2.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

//        dc.addObserver(this);
        this.dc = dc;

    }

    @Override
    public void update(Observable o, Object obj) {
        Exercise exercise = (Exercise) obj;
        txtAnw.setText(exercise.getAnswer());
        txtAssignment.setText(exercise.getAssignment());
        txtEx.setText(exercise.getName());
        txtFeedback.setText(exercise.getFeedback());
        txtCat.setText(exercise.getCategoryDescription());
//        tblViewGroupOperations.setItems(FXCollections.observableArrayList(exercise.getGroupOperations())); //deze gebruiken
       //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
//        clmValue.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDescription()))); //deze gebruiken
        
    }

}
