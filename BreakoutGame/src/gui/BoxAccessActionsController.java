/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.BoxController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxAccessActionsController extends AnchorPane implements Observer{

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private TableView<AccessCode> tblAllAccess;
    @FXML
    private TableColumn<AccessCode, Integer> clmAllAccessName;
    @FXML
    private TableView<AccessCode> tblSelectedAccess;
    @FXML
    private TableColumn<AccessCode, Integer> clmSelectedAccessNavme;
    @FXML
    private TableView<BoBAction> tblAllActions;
    @FXML
    private TableColumn<BoBAction, String> clmAllActionName;
    @FXML
    private TableView<BoBAction> tblSelectedActions;
    @FXML
    private TableColumn<BoBAction, String> clmSelectedActionName;

    private BoxController dc;
    @FXML
    private HBox hboxAccess;
    public BoxAccessActionsController(BoxController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxAccessActions.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
      JFXDepthManager.setDepth(hboxAccess, 5);
      tblAllAccess.setItems(dc.getAccessCodes());
      clmAllAccessName.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
      
      tblAllActions.setItems(dc.getActions());
      clmAllActionName.setCellValueFactory(e -> e.getValue().nameProperty());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Box box = (Box) arg;
        tblSelectedAccess.setItems(dc.getTempListAccessCodes());
        clmSelectedAccessNavme.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
        
        tblSelectedActions.setItems(dc.getTempListBoBActions());
        clmSelectedActionName.setCellValueFactory(e -> e.getValue().nameProperty());
        
        txtDescription.setText(box.getDescription());
        txtName.setText(box.getName());
    }

   
    
}
