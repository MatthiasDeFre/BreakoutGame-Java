/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Exercise;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseDetailScreenController extends GridPane implements Observer{

    @FXML
    private Label lblExercise;
    @FXML
    private Label lblDescription;

    @Override
    public void update(Observable o, Object obj) {
        Exercise exercise = (Exercise) obj;
        lblExercise.setText("fdfdfd");
    }

}
