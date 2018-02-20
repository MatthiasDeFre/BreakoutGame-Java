/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import java.util.Locale;
//import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alexander
 */
public class SceneController {

    public boolean switchScene(ActionEvent event, String FXML) {
        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource(FXML)); // ,ResourceBundle.getBundle("lang/Lang", Locale.getDefault())
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (Exception e) {
            System.out.printf("Error switching scene");
        }
        return true;
    }

    public boolean screenInit(String FXML) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error switching scene");
        }
        
        return true;
    }
}
