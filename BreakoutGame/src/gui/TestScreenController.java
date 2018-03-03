/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.BoxController;
import domain.Exercise;
import domain.ExerciseDomainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class TestScreenController extends AnchorPane {
    
    private BoxController dc;
    @FXML
    private TableView<Box> tblBox;
    @FXML
    private TableColumn<Box, String> clmName;
    @FXML
    private TableColumn<Box, String> clmDescription;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDescription;
    @FXML
    private TableView<BoBAction> tblSelectedActions;
    @FXML
    private TableColumn<Exercise, String> clmDescriptionSelected;
    @FXML
    private TableView<AccessCode> tblSelectedAccess;
    @FXML
    private TableColumn<AccessCode, Integer> clmSelecCode;
    @FXML
    private TableColumn<BoBAction, String> clmDescriptionAll;
    @FXML
    private TableView<AccessCode> tblAllAccess;
    @FXML
    private TableColumn<AccessCode, Integer> clmAllCode;
    @FXML
    private Button btnAddAction;
    @FXML
    private Button btnRemoveCode;
    @FXML
    private Button btnAddCode;
    @FXML
    private Button btnRemoveEx;
    @FXML
    private TableView<Exercise> tblAllEx;
    @FXML
    private TableColumn<Exercise, String> clmClassAll;
    @FXML
    private TableColumn<Exercise, String> clmNameAll;
    @FXML
    private Button btnAddEx;
    @FXML
    private TableView<Exercise> tblSelectedEx;
    @FXML
    private TableColumn<Exercise, String> clmClassSelec;
    @FXML
    private TableColumn<Exercise, String> clmNameSelec;
    @FXML
    private Button btnRemoveAction;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<BoBAction> tblActionsAll;

    public TestScreenController(BoxController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("testScreen2.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }
        this.dc = dc;
   
       tblBox.setItems(dc.getBoxes());
       clmName.setCellFactory(e -> {
           return new TableCell<Box, String>() {
                  @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                
                setText(empty ? "" : getItem());
                setGraphic(null);
                 TableRow<Box> currentRow = getTableRow();
                 
                if(!isEmpty()) {
                if(!currentRow.getItem().isValidBox())
                     currentRow.setStyle("-fx-background-color:lightcoral");
                else
                    currentRow.setStyle("-fx-background-color:white");
                } 
            }
           };
       });
       clmName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));
       clmDescription.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getDescription()));
       
       
       
       tblAllEx.setItems(dc.getExercises());
       
       clmClassAll.setCellValueFactory(e -> e.getValue().categoryProperty());
       clmNameAll.setCellValueFactory(e -> e.getValue().assignmentProperty());
       
       tblAllAccess.setItems(dc.getAccessCodes());
       clmAllCode.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
       
       tblActionsAll.setItems(dc.getActions());
       clmDescriptionAll.setCellValueFactory(e -> e.getValue().nameProperty());
       
    }

    @FXML
    private void btnAddAction(ActionEvent event)
    {
    }

    @FXML
    private void removeCode(ActionEvent event)
    {
    }

    @FXML
    private void addCode(ActionEvent event)
    {
    }

    @FXML
    private void removeExercise(ActionEvent event)
    {
    }

    @FXML
    private void addExercise(ActionEvent event)
    {
    }

    @FXML
    private void removeAction(ActionEvent event)
    {
    }

    @FXML
    private void createNewBox(ActionEvent event)
    {
    }

    @FXML
    private void saveChangesBox(ActionEvent event)
    {
    }

}
