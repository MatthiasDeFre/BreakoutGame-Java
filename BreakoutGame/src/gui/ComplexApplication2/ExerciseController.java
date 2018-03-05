/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ComplexApplication2;

import domain.ExerciseDomainController;
import gui.ExerciseDetailScreenController2;
import gui.ExerciseDetailsPaneMidController;
import gui.ExercisesGroupOperationsPaneRightController;
import gui.ExercisesPaneLeftController;
import gui.GroupScreenController;
import gui.StartScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseController extends VBox {

    private ExerciseDetailScreenController2 exerciseDetailScreen2;
    private StartScreenController startScreen;
    private GroupScreenController groupScreen;
    private ExercisesPaneLeftController screen1;
    private ExerciseDetailsPaneMidController screen2;
    private ExercisesGroupOperationsPaneRightController screen3;

    private ExerciseDomainController dc;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Color x4;
    @FXML
    private Font x3;

    public ExerciseController(ExerciseDomainController dc) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Exercise.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;
        startScreen = new StartScreenController(dc);
        exerciseDetailScreen2 = new ExerciseDetailScreenController2(dc);
        groupScreen = new GroupScreenController(dc);
        screen1 = new ExercisesPaneLeftController(dc);
        screen2 = new ExerciseDetailsPaneMidController(dc);
        screen3 = new ExercisesGroupOperationsPaneRightController(dc);

//        testScreen = new TestScreenController(dc);
//        exerciseDetailScreen = new ExerciseDetailScreenController(dc);
//        this.add(startScreen, 0, 0);
//        this.add(testScreen, 1, 0);
        dc.addObserverExercise(screen2);
        dc.addObserverExercise(screen3);
        
        splitPane.getItems().add(screen1);
        splitPane.getItems().add(screen2);
        splitPane.getItems().add(screen3);

        this.setStyle("-fx-background-image: url('https://i.imgur.com/E1METw5.jpg')");
    }

}
