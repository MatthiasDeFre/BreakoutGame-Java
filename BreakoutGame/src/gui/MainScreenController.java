/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import domain.ExerciseDomainController;
import domain.SceneName;
import gui.ComplexApplication2.ExerciseController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author geers
 */
public class MainScreenController extends AnchorPane implements Observer{
    private ExerciseDomainController dc;
    @FXML
    private JFXButton btnBox;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXButton btnExercise;
    @FXML
    private HBox hBox;

    public MainScreenController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }

        this.dc=dc;
        hBox.setAlignment(Pos.CENTER);
//        btnBox.setOnAction(this::btnBoxStartScreen);
//        btnAnder.setOnAction(this::btnAnderScreen);
//         String image = MainScreenController.class.getResource("boeken.jpg").toExternalForm();
//        AnchorPane.setStyle("-fx-background-image: url('" + image + "'); " +
//           "-fx-background-position: center center; " +
//           "-fx-background-repeat: stretch;");
    }

    
    @FXML
    private void btnBoxStartScreen(ActionEvent event) {
        SceneController4.createScene(SceneName.STARTSCREEN);
        SceneController4.switchScene(SceneName.STARTSCREEN);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void btnExerciseStart(ActionEvent event) {
        SceneController4.createScene(SceneName.EXERCISEMAINSCREEN2);
     SceneController4.switchScene(SceneName.EXERCISEMAINSCREEN2);
    }
}
