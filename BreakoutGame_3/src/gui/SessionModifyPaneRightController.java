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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneRightController extends AnchorPane {

    @FXML
    private Button btnTest;

    private ExerciseDomainController dc;

    public SessionModifyPaneRightController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneRight.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
//xxx
        this.dc = dc;
//        btnTest.getStylesheets().add("bootstrapfx.css");
//        btnTest.getStyleClass().setAll("btn", "btn-danger");
    }

}
