/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import domain.Box;
import domain.BoxController;
import domain.Exercise;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
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
    @FXML
    private JFXButton btnSendToTemp;
    @FXML
    private JFXButton btnRemoveFromTemp;
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
        ObservableList exAll = dc.getExercises();
        tblAllExercise.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblAllExercise.setItems(exAll);
        clmALLClass.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmAllName.setCellValueFactory(e -> e.getValue().assignmentProperty());
        
        ObservableList exSel = dc.getTempListExercises();
         tblSelectedExercise.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblSelectedExercise.setItems(exSel);
        clmSelectedClass.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmSelectedName.setCellValueFactory(e -> e.getValue().assignmentProperty());
       
        btnSendToTemp.disableProperty().bind(Bindings.size(exAll).isEqualTo(0));
        btnRemoveFromTemp.disableProperty().bind(Bindings.size(exSel).isEqualTo(0));
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Box box = (Box) arg;
        
    }

    @FXML
    private void sendToTemp(ActionEvent event)
    {
        dc.addToTempList(tblAllExercise.getSelectionModel().getSelectedItems());
        if(tblAllExercise.getItems().size() > 0) 
            tblAllExercise.getSelectionModel().select(0);
    }

    @FXML
    private void removeFromTemp(ActionEvent event)
    {
          dc.removeFromTempList(tblSelectedExercise.getSelectionModel().getSelectedItems());
           if(tblSelectedExercise.getItems().size() > 0) 
            tblSelectedExercise.getSelectionModel().select(0);
    }

    private void setSelectedEx() {
        
    }
}
