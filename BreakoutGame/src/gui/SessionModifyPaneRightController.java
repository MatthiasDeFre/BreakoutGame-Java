/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import domain.ExerciseDomainController;
import domain.Session;
import domain.SessionController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneRightController extends AnchorPane implements Observer {

    private SessionController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtClass;
    @FXML
    private JFXDatePicker DPDate;
    @FXML
    private JFXToggleButton TBFeedback;
    @FXML
    private JFXToggleButton TBTile;

    public SessionModifyPaneRightController(SessionController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneRight.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
    }

    @Override
    public void update(Observable o, Object obj) {
        Session session = (Session) obj;
        txtName.setText(session.getName());
        txtDescription.setText(session.getDescription());
        txtClass.setText(session.getClassRoom().getStudentClassName());
        DPDate.setValue(session.getActivationDate());
        TBFeedback.setSelected(session.isFeedback());
        TBTile.setSelected(session.isTile());
    }

}
