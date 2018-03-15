/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
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

    public BoBActionDetailController(BoxController dc)
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
        
        btnRemove.setDisable(false);
        btnSave.disableProperty().bind(txtActionName.textProperty().isEmpty());
       txtActionName.setDisable(false);
        BoBAction action = (BoBAction) arg;
        txtActionName.setText(action.getName());
        
    }

    @FXML
    private void removeAction(ActionEvent event)
    {
        dc.removeAction();
    }

  
    
    
    
}
