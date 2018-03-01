/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class ExerciseDetailScreen2Controller implements Initializable {

    @FXML
    private TextField txtAnw;
    @FXML
    private TextField txtFeedback;
    @FXML
    private TextField txtAssignment;
    @FXML
    private ComboBox<?> cmbCategory;
    @FXML
    private TextField txtEx;
    @FXML
    private Button Save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

  
}
