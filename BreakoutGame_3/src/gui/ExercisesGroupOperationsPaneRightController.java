/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import domain.BoBAction;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.GroupOperation;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    private ExerciseDomainController dc;
    @FXML
    private TableColumn<GroupOperation, String> clmDescription;
    @FXML
    private TableColumn<GroupOperation, String> clmSelectedDescription;
    @FXML
    private AnchorPane pane;
    private TableColumn<GroupOperation, String> tblViewGroupOperationsAanpassen;
    @FXML
    private HBox hboxGroupOpActions;
    @FXML
    private TableView<GroupOperation> tblAvailableGrOps;
    @FXML
    private TableColumn<GroupOperation, String> clmAvailableGrOpName;
    @FXML
    private VBox vboxGoal;
    @FXML
    private TableView<Goal> tblViewGroals;
    @FXML
    private TableColumn<Goal, String> clmAllGoalName;
    @FXML
    private JFXButton btnRightGoal;
    @FXML
    private JFXButton btnLeftGoal;
    @FXML
    private TableView<Goal> tblViewSelectedGroals;
    @FXML
    private TableColumn<Goal, String> clmSelectedGoalName;

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
        tblViewGroupOperations.setItems(dc.getFilteredItems(GroupOperation.class));
        ObservableList groupOpTemp = dc.getGroupOperationsTemp();
       tblViewSelectedGroupOperations.setItems(groupOpTemp);
               clmSelectedDescription.setCellValueFactory(e -> e.getValue().descriptionProperty());
        System.out.println(dc.getFilteredItems(GroupOperation.class));
        
        GroupOperationDetailController groupOperationDetailController = new GroupOperationDetailController(dc);
        dc.addObserverGroupOperation(groupOperationDetailController);
        hboxGroupOpActions.getChildren().add(groupOperationDetailController);
        //TODO CHANGE TO FULL LIST
        tblAvailableGrOps.itemsProperty().bind(tblViewGroupOperations.itemsProperty());
        tblAvailableGrOps.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if (newSelection != null)
                    {
                        dc.setManagerModeGroupOp(PersistMode.UPDATE);
                        dc.setSelectedItem(newSelection);
                    }
        });
        clmAvailableGrOpName.setCellValueFactory(e -> e.getValue().descriptionProperty());
        
        tblViewGroals.setItems(dc.getFilteredItems(Goal.class));
        clmAllGoalName.setCellValueFactory(e -> e.getValue().code());
        tblViewSelectedGroals.setItems(dc.getGoalsTemp());
        clmSelectedGoalName.setCellValueFactory(e -> e.getValue().code());
        
        btnRight.setDisable(true);
        btnLeft.disableProperty().bind(Bindings.size(groupOpTemp).isEqualTo(0));
      
        //Goal editing
        GoalDetailController goalDetailController = new GoalDetailController(dc);
        vboxGoal.getChildren().add(goalDetailController);
        this.dc.addObserverGoal(goalDetailController);
        tblViewGroals.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if (newSelection != null)
                    {
                        dc.setSelectedItem(newSelection);
                    }
        });
        
    }

    @FXML
    private void btnRightOnAction(ActionEvent event) {
        dc.addToTempList(tblViewGroupOperations.getSelectionModel().getSelectedItems());
         if(tblViewGroupOperations.getItems().size() > 0) 
                tblViewGroupOperations.getSelectionModel().select(0);
    }

    @FXML
    private void btnLeftOnAction(ActionEvent event) {
           dc.removeFromTempList(tblViewSelectedGroupOperations.getSelectionModel().getSelectedItems());
            if(tblViewSelectedGroupOperations.getItems().size() > 0) 
                tblViewSelectedGroupOperations.getSelectionModel().select(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        Exercise exercise = (Exercise) arg;
        btnRight.setDisable(false);
     //deze gebruiken
        //     exercise.addGroupOperationTemp(new GroupOperation(OperationCategory.MULTIPLY, "5"));
        //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
      //deze gebruiken
        dc.changeFilterGroupOperations(exercise.getGroupOperations());
        dc.changeFilterGoal(exercise.getGoals().stream().collect(Collectors.toList()));
        //     tblViewGroupOperations.setItems(dc.getGroupOperations());
    }

    @FXML
    private void addGoalTolTemp(ActionEvent event)
    {
        
          dc.addToTempList(tblViewGroals.getSelectionModel().getSelectedItems());
          if(tblViewGroals.getItems().size() > 0) 
                tblViewGroals.getSelectionModel().select(0);
     
    }

    @FXML
    private void removeGoalFromTemp(ActionEvent event)
    {
    
         dc.removeFromTempList(tblViewSelectedGroals.getSelectionModel().getSelectedItems());
         if(tblViewSelectedGroals.getItems().size() > 0) 
                tblViewSelectedGroals.getSelectionModel().select(0);
        
    }

}
