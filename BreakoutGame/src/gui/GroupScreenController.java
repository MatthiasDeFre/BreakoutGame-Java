/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Exercise;
import domain.GroupOperation;
import domain.UseCaseExerciseAdminController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class GroupScreenController extends GridPane implements Observer{

    @FXML
    private TableView<GroupOperation> tblViewGroupOperations;
    @FXML
    private TableColumn<GroupOperation, String> clmDescription;
    @FXML
    private TableView<GroupOperation> tblViewSelectedGroupOperations;

    //Non fxml attributes
    private UseCaseExerciseAdminController dc;
    @FXML
    private TableColumn<GroupOperation, String> tblSelectedDesc;

    public GroupScreenController(UseCaseExerciseAdminController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

//        dc.addObserver(this);
        this.dc = dc;
        clmDescription.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDescription())));
        tblViewGroupOperations.setItems(dc.getGroupOperations());
        System.out.println(dc.getGroupOperations());

    }

    @Override
    public void update(Observable o, Object arg)
    {
         Exercise exercise = (Exercise) arg;
         tblViewSelectedGroupOperations.setItems(exercise.getGroupOperationsObservableList()); //deze gebruiken
       //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
         tblSelectedDesc.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDescription()))); //deze gebruiken
         dc.changeFilterGroupOperations(exercise.getGroupOperations());
         tblViewGroupOperations.setItems(dc.getGroupOperations());
    }

}
