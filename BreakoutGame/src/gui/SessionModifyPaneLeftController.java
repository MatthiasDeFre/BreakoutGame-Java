/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.ExerciseDomainController;
import domain.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneLeftController extends AnchorPane {

    @FXML
    private TableColumn<Session, String> tblSessions;
    
    private ExerciseDomainController dc;

    public SessionModifyPaneLeftController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneLeft.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;
//        btnTest.getStylesheets().add("bootstrapfx.css");
//        btnTest.getStyleClass().setAll("btn", "btn-danger");
    }

}
