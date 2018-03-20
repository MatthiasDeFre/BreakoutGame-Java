/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import domain.Box;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.PersistMode;
import domain.Session;
import domain.SessionController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneRightController extends AnchorPane implements Observer {

    private SessionController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtClass;
    @FXML
    private JFXDatePicker DPDate;
    @FXML
    private JFXToggleButton TBFeedback;
    @FXML
    private JFXToggleButton TBTile;
    @FXML
    private TableView<Box> tblBOBS;
    @FXML
    private TableColumn<Box, String> clmBob;
    @FXML
    private JFXTextField txtNaamBox;
    @FXML
    private TableView<Goal> tblGoal;
    @FXML
    private TableColumn<Goal, String> clmGoal;

    public SessionModifyPaneRightController(SessionController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneRight.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        clmBob.setCellValueFactory(e -> e.getValue().nameProperty());
        clmGoal.setCellValueFactory(e -> e.getValue().code());
        tblBOBS.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
            }
        });
    }

    @Override
    public void update(Observable o, Object obj) {
        if (obj instanceof Session) {
            Session session = (Session) obj;
            txtName.setText(session.getName());
            txtDescription.setText(session.getDescription());
            txtClass.setText(session.getClassRoom().getStudentClassName());
            DPDate.setValue(session.getActivationDate());
            TBFeedback.setSelected(session.isFeedback());
            TBTile.setSelected(session.isTile());
            ObservableList bobs = dc.getFilteredItems(Box.class);
            tblBOBS.setItems(bobs);
        }

        if (obj instanceof Box) {
            ObservableList goals = dc.getGoals();
            tblGoal.setItems(goals);
            txtNaamBox.setText(((Box) obj).getName());
        }

    }
    
    private void generateGroups() {
        //int amount;
        /*if(btntile) {
            dc.getAmountOfStudents();
        } else {
            amount = txtAmount; 
        }
        */
        //dc.generateGroups(/*amount*/, /*btnEmptyGroup*/);
    }
    private void generatePaths() {
        
        
    }
}
