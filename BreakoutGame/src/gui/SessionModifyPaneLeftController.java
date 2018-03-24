/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    
    private JFXDialog dialog;
    
    public SessionModifyPaneLeftController(SessionController dc, JFXDialog dialog) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneLeft.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;
        this.dialog = dialog;
        clmSessions.setCellValueFactory(e -> e.getValue().nameProperty());
        tblSessions.setItems(dc.getFilteredItems(Session.class));
      //  , (tblSessions.getSelectionModel().getSelectedItem() != null && tblSessions.getSelectionModel().getSelectedItem().getSessionStatus() != SessionStatus.ACTIVATED))
        btnVerwijder.disableProperty().bind(Bindings.size(tblSessions.getItems()).isEqualTo(0));
      
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
        try
        {
            dc.removeSession();
        } catch (Exception e)
        {
            showError(e);
        }
    }
     private void showComponentDialog(Parent pane) {
            JFXDialogLayout layout = new JFXDialogLayout();
           layout.setAlignment(Pos.BOTTOM_RIGHT);
          layout.setPadding(Insets.EMPTY);
            layout.setBody(pane);
            JFXButton okButton = new JFXButton("Cancel");
            okButton.setStyle("-fx-background-color: #112959;");
            okButton.setOnMouseClicked(e -> dialog.close());
            layout.setActions(okButton);
          
            dialog.setContent(layout);
            dialog.show();
    }
    
    private void showError(Exception e) {
          VBox erros = new VBox();
            erros.getChildren().addAll(new Label(e.getMessage(), new JFXButton("Ok")));
            showComponentDialog(erros);
    }
}
