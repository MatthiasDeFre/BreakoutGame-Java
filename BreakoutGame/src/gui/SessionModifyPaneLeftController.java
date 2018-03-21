/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import domain.BoBGroup;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.GroupOperation;
import domain.PersistMode;
import domain.Session;
import domain.SessionController;
import domain.SessionStatus;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneLeftController extends AnchorPane {

    @FXML
    private TableView<Session> tblSessions;

    private SessionController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<Session, String> clmSessions;
    @FXML
    private JFXButton btnOpslaan;
    @FXML
    private JFXButton btnVerwijder;

    public SessionModifyPaneLeftController(SessionController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneLeft.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;

        clmSessions.setCellValueFactory(e -> e.getValue().nameProperty());
        tblSessions.setItems(dc.getFilteredItems(Session.class));

        tblSessions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dc.setManagerMode(Session.class, PersistMode.UPDATE);
                setSelectedItemToDc();
            }
        });

    }

    private void setSelectedItemToDc() {
        dc.setManagerMode(Session.class, PersistMode.UPDATE);
        dc.setSelectedItem(tblSessions.getSelectionModel().getSelectedItem());
        dc.setSelectedItem(new BoBGroup());
    }

    @FXML
    private void btnOpslaanOnAction(ActionEvent event)
    {
        dc.setSelectedItem(new Session());
        dc.setManagerMode(Session.class, PersistMode.NEW);
    }

    @FXML
    private void btnVerwijderOnAction(ActionEvent event)
    {
        dc.removeSession();
    }

}
