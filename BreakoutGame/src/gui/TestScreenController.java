/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.ExerciseDomainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class TestScreenController extends BorderPane {
    
    private ExerciseDomainController dc;

    public TestScreenController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("testScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }
        this.dc = dc;
    }

}
