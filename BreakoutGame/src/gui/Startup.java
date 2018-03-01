/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.SceneName;
import domain.ExerciseController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistence.Seeder;
import util.LangConfig;
import static javafx.application.Application.launch;

/**
 *
 * @author Alexander
 */
public class Startup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Seeder.seedDatabaseWithStartData();
        LangConfig.setLang();

//        StartScreenController root = new StartScreenController();
//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//        Scene scene = new Scene(root, primaryScreenBounds.getMinX(), primaryScreenBounds.getMinY());

        SceneController2 sc2 = new SceneController2(new ExerciseController(), primaryStage);
        
        Image icon = new Image("/gui/assets/img/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("BOB Manager");
//        primaryStage.setScene(scene);
        sc2.createScene(SceneName.EXERCISEMAINSCREEN2);
        sc2.switchScene(SceneName.EXERCISEMAINSCREEN2);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
