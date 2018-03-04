/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private JFXButton btnSave;
    @FXML
    private HBox hBoxActions;
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
            JFXDepthManager.setDepth(hBoxActions, 5);
      tblAllAccess.setItems(dc.getAccessCodes());
      clmAllAccessName.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
    
      tblAllActions.setItems(dc.getActions());
      clmAllActionName.setCellValueFactory(e -> e.getValue().nameProperty());
      
         tblAllAccess.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if (newSelection != null)
                    {
                        HBox test = new HBox(new JFXTextField(String.valueOf(newSelection.getCode())), new JFXButton("Save"));
                        test.setPrefWidth(tblAllAccess.getWidth());
                        JFXPopup popup = new JFXPopup(test);
                        if(tblAllAccess.getSelectionModel().getSelectedIndex() == tblAllAccess.getItems().size() - 1)
                         popup.show(tblAllAccess, PopupVPosition.TOP, PopupHPosition.LEFT);
                        else
                              popup.show(tblAllAccess, PopupVPosition.TOP, PopupHPosition.LEFT);
                        System.out.println(tblAllAccess.getSelectionModel().getSelectedIndex());
                    }
        });
      
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

    @FXML
    private void saveBox(ActionEvent event)
    {
        dc.saveBox(txtName.getText(), txtDescription.getText());
    }

   
    
}
