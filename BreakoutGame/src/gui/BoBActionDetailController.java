/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import domain.BoBAction;
import domain.BoxController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoBActionDetailController extends AnchorPane implements Observer{

    /**
     * Initializes the controller class.
     */
    private BoxController dc;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXTextField txtActionName;
    @FXML
    private JFXButton btnRemove;

    private JFXDialog dialog;
    public BoBActionDetailController(BoxController dc, JFXDialog dia)
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("BoBActionDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        dc.addObserverAction(this);
        btnSave.setDisable(true);
        btnRemove.setDisable(true);
        txtActionName.setDisable(true);
        this.dialog = dia;
    }

    @FXML
    private void addNewAction(ActionEvent event)
    {
        dc.setManagerMode(BoBAction.class, PersistMode.NEW);
        dc.setSelectedItem(new BoBAction());
        txtActionName.requestFocus();
    }

    @FXML
    private void saveAction(ActionEvent event)
    {
        dc.saveAction(txtActionName.getText());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        
       
        BoBAction action = (BoBAction) arg;
        if(!action.getName().equals("Zoek een kist")) {
             btnRemove.setDisable(false);
        btnSave.disableProperty().bind(txtActionName.textProperty().isEmpty());
       txtActionName.setDisable(false);
        } else {
              btnRemove.setDisable(true);
        }
        
        txtActionName.setText(action.getName());
        
    }

    @FXML
    private void removeAction(ActionEvent event)
    {
        try {
            dc.removeAction(); 
        } catch(IllegalArgumentException ex) {
            setErrorDialog(ex);
        }
      
    }
         private void setErrorDialog(Exception ex) {
         JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(ex.getMessage()));
            JFXButton okButton = new JFXButton("OK");
            okButton.setOnMouseClicked(e -> dialog.close());
              okButton.setStyle("-fx-background-color: #112959;");
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
    }

  
    
    
    
}
