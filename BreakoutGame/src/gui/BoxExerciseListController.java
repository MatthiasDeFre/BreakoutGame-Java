/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.BoxController;
import domain.Exercise;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxExerciseListController extends AnchorPane implements Observer{
    private BoxController dc;
    @FXML
    private TableView<Exercise> tblAllExercise;
    @FXML
    private TableColumn<Exercise, String> clmALLClass;
    @FXML
    private TableColumn<Exercise, String> clmAllName;
    @FXML
    private TableView<Exercise> tblSelectedExercise;
    @FXML
    private TableColumn<Exercise, String> clmSelectedClass;
    @FXML
    private TableColumn<Exercise, String> clmSelectedName;
    public BoxExerciseListController(BoxController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxExerciseList.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        tblAllExercise.setItems(dc.getExercises());
        clmALLClass.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmAllName.setCellValueFactory(e -> e.getValue().categoryProperty());
       
    }

    @Override
    public void update(Observable o, Object arg)
    {
        tblSelectedExercise.setItems(dc.getTempListExercises());
        clmSelectedClass.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmSelectedName.setCellValueFactory(e -> e.getValue().assignmentProperty());
    }

    
}
