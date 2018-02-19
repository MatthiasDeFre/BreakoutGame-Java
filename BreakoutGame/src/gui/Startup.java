/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alexander
 */
public class Startup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        StartScreenController root = new StartScreenController();

        Scene scene = new Scene(root, 1000, 700);

        primaryStage.setTitle("BOB Manager");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
