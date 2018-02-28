/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ComplexApplication2;

import domain.UseCaseExerciseAdminController;
import gui.ExerciseDetailScreenController2;
import gui.GroupScreenController;
import gui.StartScreenController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
public class ComplexApplication_cssController extends VBox {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Color x4;
    @FXML
    private Font x3;

    private ExerciseDetailScreenController2 exerciseDetailScreen2;
    private StartScreenController startScreen;
    private GroupScreenController groupScreen;

//    private TestScreenController testScreen;
//    private ExerciseDetailScreenController exerciseDetailScreen;
    private UseCaseExerciseAdminController dc;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private AnchorPane scrollpane;
    @FXML
    private AnchorPane anchor2;

    public ComplexApplication_cssController(UseCaseExerciseAdminController dc) {
        this.dc = dc;
        startScreen = new StartScreenController(dc);
        exerciseDetailScreen2 = new ExerciseDetailScreenController2(dc);
        groupScreen = new GroupScreenController(dc);

//        testScreen = new TestScreenController(dc);
//        exerciseDetailScreen = new ExerciseDetailScreenController(dc);
//        this.add(startScreen, 0, 0);
//        this.add(testScreen, 1, 0);
        dc.addObserver(exerciseDetailScreen2);
        dc.addObserver(groupScreen);
        anchor1.getChildren().add(startScreen);
        this.setStyle("-fx-background-image: url('https://i.imgur.com/E1METw5.jpg')");
    }

}
