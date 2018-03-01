/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.ListStudentController;
import domain.SceneName;
import domain.UseCaseExerciseAdminController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persistence.Seeder;
import util.LangConfig;

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

        sc3.createScene(SceneName.STUDENTSSCREEN);
        sc3.switchScene(SceneName.STUDENTSSCREEN);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
