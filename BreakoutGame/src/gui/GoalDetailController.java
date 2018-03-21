/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class GoalDetailController extends AnchorPane implements Observer{

    @FXML
    private JFXTextField txtGoalName;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;

    private ExerciseDomainController dc;
    
    private JFXDialog dialog;

    public GoalDetailController(ExerciseDomainController dc, JFXDialog dia)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GoalDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;
        this.dialog = dia;
    }
    
    
    
    @FXML
    private void addNewGoal(ActionEvent event)
    {
        dc.setManagerMode(Goal.class, PersistMode.NEW);
        dc.setSelectedItem(new Goal());
    }

    @FXML
    private void saveGoal(ActionEvent event)
    {
        dc.saveGoal(txtGoalName.getText());
        dc.setManagerMode(Goal.class, PersistMode.UPDATE);
    }

    @FXML
    private void deleteGoal(ActionEvent event)
    {
        try {
            dc.deleteGoal();
        } catch(IllegalArgumentException e) {
            setErrorDialog(e);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Goal goal = (Goal) arg;
        txtGoalName.setText(goal.getCode());
    }
    
          private void setErrorDialog(Exception ex) {
         JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(ex.getMessage()));
            JFXButton okButton = new JFXButton("OK");
            okButton.setOnMouseClicked(e -> dialog.close());
              okButton.setStyle("-fx-background-color: #112959;");
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
    }

    
}
