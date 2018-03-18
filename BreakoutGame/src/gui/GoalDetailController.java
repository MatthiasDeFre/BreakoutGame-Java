/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domain.ExerciseDomainController;
import domain.Goal;
import gui.ComplexApplication2.ExerciseController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    public GoalDetailController(ExerciseDomainController dc)
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
    }
    
    
    
    @FXML
    private void addNewGoal(ActionEvent event)
    {
    }

    @FXML
    private void saveGoal(ActionEvent event)
    {
    }

    @FXML
    private void deleteGoal(ActionEvent event)
    {
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Goal goal = (Goal) arg;
        txtGoalName.setText(goal.getCode());
    }
    
}
