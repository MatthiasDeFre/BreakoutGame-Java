/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.GroupOperation;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExercisesGroupOperationsPaneRightController extends AnchorPane implements Observer {

    @FXML
    private TableView<GroupOperation> tblViewGroupOperations;
    @FXML
    private JFXButton btnRight;
    @FXML
    private JFXButton btnLeft;
    @FXML
    private TableView<GroupOperation> tblViewSelectedGroupOperations;
    @FXML
    private JFXComboBox<GroupOperation> cmbGroupOperations;
    @FXML
    private JFXButton btnAddGroupOperation;

    private ExerciseDomainController dc;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TableColumn<GroupOperation, String> clmDescription;
    @FXML
    private TableColumn<GroupOperation, String> clmSelectedDescription;

    public ExercisesGroupOperationsPaneRightController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExercisesGroupOperationsPaneRight.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

//        dc.addObserver(this);
        this.dc = dc;
        clmDescription.setCellValueFactory(e -> e.getValue().descriptionProperty());
        tblViewGroupOperations.setItems(dc.getGroupOperations());
        System.out.println(dc.getGroupOperations());

    }

    @FXML
    private void btnRightOnAction(ActionEvent event) {
    }

    @FXML
    private void btnLeftOnAction(ActionEvent event) {
    }

    @Override
    public void update(Observable o, Object arg) {
        Exercise exercise = (Exercise) arg;
        tblViewSelectedGroupOperations.setItems(dc.getGroupOperationsTemp()); //deze gebruiken
        //     exercise.addGroupOperationTemp(new GroupOperation(OperationCategory.MULTIPLY, "5"));
        //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
        clmSelectedDescription.setCellValueFactory(e -> e.getValue().descriptionProperty()); //deze gebruiken
        dc.changeFilterGroupOperations(exercise.getGroupOperations());
        //     tblViewGroupOperations.setItems(dc.getGroupOperations());
    }

}
