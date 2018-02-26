/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.SceneName;
import domain.UseCaseExerciseAdminController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Alexander
 */
public class SceneController2 {

    private static UseCaseExerciseAdminController dc;
    private static Stage primaryStage;
    private static SceneFactory sf;
    private static Scene scene; 

    public SceneController2(UseCaseExerciseAdminController dc, Stage primaryStage) {
        this.dc = dc;
        this.primaryStage = primaryStage;
        sf = new SceneFactory(dc);
    }
    
    public static void createScene(SceneName sn) {
        scene = sf.createScene(sn);
    } 

    public static void switchScene(SceneName sn) {

        primaryStage.setScene(scene);
    }

}
