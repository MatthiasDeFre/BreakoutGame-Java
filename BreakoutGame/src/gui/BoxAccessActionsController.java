/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.BoxController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

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
    @FXML
    private JFXButton btnAddAccess;
    @FXML
    private JFXButton btnRemoveAccess;
    @FXML
    private JFXButton btnAddAction;
    @FXML
    private JFXButton btnRemoveAction;
    @FXML
    private GridPane grid;
    private StackPane testS;
    @FXML
    private HBox hBoxInfo;
    
    private JFXTextField txtField;
    private JFXPopup popup;
    private JFXDialog dialog;
    
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
        
       
     
      JFXDepthManager.setDepth(tblAllAccess, 1);
      JFXDepthManager.setDepth(tblSelectedAccess, 1);
      JFXDepthManager.setDepth(tblAllActions, 1);
      JFXDepthManager.setDepth(tblSelectedActions, 1);
      tblAllAccess.setItems(dc.getFilteredItems(AccessCode.class));
      clmAllAccessName.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
    
      tblAllActions.setItems(dc.getFilteredItems(BoBAction.class));
      clmAllActionName.setCellValueFactory(e -> e.getValue().nameProperty());
      
      tblAllAccess.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        selectAccessCode(newSelection);

                    }
        });

       tblSelectedAccess.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        selectAccessCode(newSelection);

                    }
        });
        tblAllAccess.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblAllActions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblSelectedAccess.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblSelectedActions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     
        
        btnSave.setDisable(true);
        
    }

    private void selectAccessCode(AccessCode newSelection)
    {
        dc.setManagerMode(AccessCode.class, PersistMode.UPDATE);
        dc.setSelectedItem(newSelection);
        JFXButton btnSaveLoc =  new JFXButton("Save");
        
        //  JFXTextField txtField = new JFXTextField(String.valueOf(newSelection.getCode()));
        popup = new JFXPopup();
        txtField = new JFXTextField();
        txtField.setText(String.valueOf(newSelection.getCode()));
        txtField.setPromptText("Actie veranderen");
        txtField.setPrefWidth(hBoxInfo.getWidth());
        txtField.setPrefHeight(USE_COMPUTED_SIZE);
        txtField.setPadding(new Insets(5));
        
     
   /*     btnSaveLoc.setOnMouseClicked(e -> {
            dc.saveAccessCode(Integer.valueOf(txtField.getText()));
        });*/
        
        HBox test = new HBox(txtField, btnSaveLoc);
        test.setPrefWidth(hBoxInfo.getWidth());
        
        
        
        popup.setPopupContent(test);
        
        popup.show(hBoxInfo, PopupVPosition.TOP, PopupHPosition.LEFT);
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
          btnSave.setDisable(false);
    }

    @FXML
    private void saveBox(ActionEvent event)
    { 
        try {
             dc.saveBox(txtName.getText(), txtDescription.getText());
        } catch(IllegalArgumentException e) {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(e.getMessage()));
            JFXButton okButton = new JFXButton("OK ik zal het niet meer doen");
            okButton.setOnMouseClicked(e2 -> dialog.close());
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
        }
       
    }

    @FXML
    private void addAccess(ActionEvent event)
    {
        dc.addToTempList(tblAllAccess.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void removeAccess(ActionEvent event)
    {
        dc.removeFromTempList(tblSelectedAccess.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void addAction(ActionEvent event)
    {
        dc.addToTempList(tblAllActions.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void removeAction(ActionEvent event)
    {
        if(tblSelectedActions.getSelectionModel().getSelectedItems().stream().allMatch(e -> !e.getName().equals("Zoek een kist")))
           dc.removeFromTempList(tblSelectedActions.getSelectionModel().getSelectedItems());
        else {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("Je hebt proberen de actie schatkist zoeken te verwijderen, deze actie is verplicht op te namen bij elke box(tm)"));
            JFXButton okButton = new JFXButton("OK ik zal het niet meer doen");
            okButton.setOnMouseClicked(e -> dialog.close());
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
        }
    }

    public void setDialog(JFXDialog dialogLayout) {
        dialog = dialogLayout;
    }

   
    
}
