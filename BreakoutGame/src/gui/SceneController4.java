/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.ExerciseDomainController;
import domain.ListStudentController;
import domain.SceneName;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author geers
 */
public class SceneController4 {
    private static ExerciseDomainController dc;
    private static Stage primaryStage;
    private static SceneFactory sf;
    private static Scene scene; 

    public SceneController4(ExerciseDomainController dc, ListStudentController dc2,Stage primaryStage) {
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