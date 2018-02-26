/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.UseCaseExerciseAdminController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseController extends HBox {

    private ExerciseDetailScreenController exerciseDetailScreen;
    private ExerciseDetailScreenController2 exerciseDetailScreen2;
    private StartScreenController startScreen;
    
    private TestScreenController testScreen;

    private UseCaseExerciseAdminController dc;

    public ExerciseController(UseCaseExerciseAdminController dc) {
        this.dc = dc;
        startScreen = new StartScreenController(dc);
        exerciseDetailScreen = new ExerciseDetailScreenController(dc);
        exerciseDetailScreen2 = new ExerciseDetailScreenController2(dc);
        testScreen = new TestScreenController(dc);

//        this.add(startScreen, 0, 0);
//        this.add(testScreen, 1, 0);
        dc.addObserver(exerciseDetailScreen2);
        getChildren().addAll(startScreen, exerciseDetailScreen2);
        this.setStyle("-fx-background-image: url('https://i.imgur.com/E1METw5.jpg')");
        
    }
}
