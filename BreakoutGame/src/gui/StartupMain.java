/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.ExerciseDomainController;
import domain.ListStudentController;
import domain.SceneName;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persistence.Seeder;
import util.LangConfig;

/**
 *
 * @author geers
 */
public class StartupMain extends Application{

    @Override
    public void start(Stage stage) throws Exception
    {
        Seeder.seedDatabaseWithStartData();
        LangConfig.setLang();
        SceneController4 sc4=new SceneController4(new ExerciseDomainController(), new ListStudentController(),stage);
        Image icon = new Image("/gui/assets/img/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("BOB Manager");
        sc4.createScene(SceneName.MAINSCREEN);
        sc4.switchScene(SceneName.MAINSCREEN);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    }

