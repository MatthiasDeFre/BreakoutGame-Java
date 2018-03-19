/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.ListStudentController;
import domain.SceneName;
import domain.ExerciseDomainController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persistence.Seeder;
import util.LangConfig;
import static javafx.application.Application.launch;

/**
 *
 * @author geers
 */
public class Startup2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Seeder.seedDatabaseWithStartData();
        LangConfig.setLang();

        SceneController3 sc3 = new SceneController3(new ListStudentController(), primaryStage);
        
        Image icon = new Image("/gui/assets/img/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("BOB Manager");

        sc3.createScene(SceneName.MAINSCREEN);
        sc3.switchScene(SceneName.MAINSCREEN);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
