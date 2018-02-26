/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.GroupOperation;
import domain.UseCaseExerciseAdminController;
import java.io.IOException;
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
public class GroupScreenController extends GridPane {

    @FXML
    private TableView<GroupOperation> tblViewGroupOperations;
    @FXML
    private TableColumn<GroupOperation, String> clmDescription;
    @FXML
    private TableView<GroupOperation> tblViewSelectedGroupOperations;

    //Non fxml attributes
    private UseCaseExerciseAdminController dc;

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
        tblViewGroupOperations.setItems(FXCollections.observableArrayList(dc.getGroupOperations()));
        System.out.println(dc.getGroupOperations());

    }

}
