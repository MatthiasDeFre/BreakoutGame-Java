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
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.BoxController;
import domain.Exercise;
import domain.Goal;
import domain.PersistMode;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxMaxController extends AnchorPane implements Observer{

   

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
      @FXML
    private TableView<Exercise> tblAllExercise;
    @FXML
    private TableColumn<Exercise, String> clmALLClass;
    @FXML
    private TableColumn<Exercise, String> clmAllName;
    @FXML
    private TableView<Exercise> tblSelectedExercise;
    @FXML
    private TableColumn<Exercise, String> clmSelectedClass;
    @FXML
    private TableColumn<Exercise, String> clmSelectedName;
    @FXML
    private JFXButton btnSendToTemp;
    @FXML
    private JFXButton btnRemoveFromTemp;
    @FXML
    private JFXTextField txtDescription1;
    @FXML
    private JFXTextField txtName1;
    @FXML
    private TableView<Exercise> tblSelectedExercise1;
    @FXML
    private TableColumn<Exercise, String> clmSelectedClass1;
    @FXML
    private TableColumn<Exercise, String> clmSelectedName1;
    @FXML
    private TableView<AccessCode> tblSelectedAccess1;
    @FXML
    private TableColumn<AccessCode, Integer> clmSelectedAccessNavme1;
    @FXML
    private TableView<BoBAction> tblSelectedActions1;
    @FXML
    private TableColumn<BoBAction, String> clmSelectedActionName1;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXTabPane tabBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Goal> tblGoals;
    @FXML
    private TableColumn<Goal, String> clmCode;

    public BoxMaxController(BoxController dc)
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("boxMax.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        } catch (IOException ex)
        {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        
        //Things you just have to accept
       hackTooltipStartTiming(new Tooltip());
            JFXDepthManager.setDepth(tblAllAccess, 1);
      JFXDepthManager.setDepth(tblSelectedAccess, 1);
      JFXDepthManager.setDepth(tblAllActions, 1);
      JFXDepthManager.setDepth(tblSelectedActions, 1);
      //End those things
      
      
      //All access
      tblAllAccess.setItems(dc.getFilteredItems(AccessCode.class));
      clmAllAccessName.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
    
      //All action
      tblAllActions.setItems(dc.getFilteredItems(BoBAction.class));
      clmAllActionName.setCellValueFactory(e -> e.getValue().nameProperty());
      
      
        tblAllAccess.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblAllActions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblSelectedAccess.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblSelectedActions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     
        
        btnSave.setDisable(true);
        
        btnAddAccess.disableProperty().bind(Bindings.size(tblAllAccess.getItems()).isEqualTo(0));
    
      //  System.out.println(tblSelectedAccess.getItems());
        btnAddAction.disableProperty().bind(Bindings.size(tblAllActions.getItems()).isEqualTo(0));
      
        
        btnRemoveAction.setDisable(true);
        btnRemoveAccess.setDisable(true);
        
        //Exercise
        ObservableList exAll = dc.getFilteredItems(Exercise.class);
        tblAllExercise.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblAllExercise.setItems(exAll);
        clmALLClass.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmAllName.setCellValueFactory(e -> e.getValue().assignmentProperty());
        
        ObservableList exSel = dc.getTempListExercises();
         tblSelectedExercise.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblSelectedExercise.setItems(exSel);
        clmSelectedClass.setCellValueFactory(e -> e.getValue().categoryProperty());
        clmSelectedName.setCellValueFactory(e -> e.getValue().assignmentProperty());
       
        //Goals of box
         tblGoals.setItems(dc.getListGoal());
         clmCode.setCellValueFactory(e -> e.getValue().code());
        
        btnSendToTemp.disableProperty().bind(Bindings.size(exAll).isEqualTo(0));
        btnRemoveFromTemp.disableProperty().bind(Bindings.size(exSel).isEqualTo(0));
        
       
       
        
        txtName.textProperty().bindBidirectional(txtName1.textProperty());
        txtDescription.textProperty().bindBidirectional(txtDescription1.textProperty());
        
        
        tblAllExercise.setRowFactory(tableView -> {
            final TableRow<Exercise> row = new TableRow<>();

            row.hoverProperty().addListener((observable) -> 
                    {
                        final Exercise ex = row.getItem();

                        if (row.isHover() && ex != null)
                        {
                            Tooltip t = new Tooltip();
                            t.setFont(new Font("Roboto", 14));
                            StringBuilder tooltipText = new StringBuilder("Doelstellingen:\n\n");
                            if (!ex.getGoals().isEmpty())
                            {
                                ex.getGoals().stream().sorted().forEach(e
                                        -> 
                                        {
                                            tooltipText.append("* ").append(e.getCode()).append("\n");
                                });
                            } else
                            {
                                tooltipText.append("Deze oefening heeft geen doelstellingen ");
                            }

                            t.setText(tooltipText.toString());
                            System.out.println(ex.getGoals().stream().map(e -> e.getCode()).collect(Collectors.joining(" ")));
                            row.setTooltip(t);
                        }
            });

                 return row;
            });
        tblSelectedExercise.rowFactoryProperty().bind(tblAllExercise.rowFactoryProperty()); 
          //Lists of selected Box
       // tblSelectedAccess1.setItems(dc.getTempListAccessCodes());
        tblSelectedActions.setItems(dc.getTempListBoBActions());
        clmSelectedActionName.setCellValueFactory(e -> e.getValue().nameProperty());
       
         tblSelectedAccess.setItems(dc.getTempListAccessCodes());
          clmSelectedAccessNavme.setCellValueFactory(e -> e.getValue().codeProperty().asObject());
        //;
       
        
      
          
       /*  tblSelectedExercise1.setItems(dc.getTempListExercises());
         
         
        tblSelectedActions1.setItems(dc.getTempListBoBActions());
         */
        
       //Overview tables
         tblSelectedExercise1.itemsProperty().bind(tblSelectedExercise.itemsProperty());
         tblSelectedExercise1.rowFactoryProperty().bind(tblSelectedExercise.rowFactoryProperty());
           tblSelectedAccess1.itemsProperty().bind(tblSelectedAccess.itemsProperty());
           tblSelectedActions1.itemsProperty().bind(tblSelectedActions.itemsProperty());
        
           //Overview columns
          
           clmSelectedClass1.cellValueFactoryProperty().bind(clmSelectedClass.cellValueFactoryProperty());
            clmSelectedName1.cellValueFactoryProperty().bind(clmSelectedName.cellValueFactoryProperty());
           clmSelectedAccessNavme1.cellValueFactoryProperty().bind(clmSelectedAccessNavme.cellValueFactoryProperty());
           clmSelectedActionName1.cellValueFactoryProperty().bind(clmSelectedActionName.cellValueFactoryProperty());
           
//         clmSelectedClass.setCellFactory(e -> e);
        btnRemoveAccess.disableProperty().bind(Bindings.size(tblSelectedAccess.getItems()).isEqualTo(0));
        System.out.println(tblSelectedActions.getItems().size());
        btnRemoveAction.disableProperty().bind(Bindings.size(tblSelectedActions.getItems()).lessThan(2));
    }

    

     private void selectAccessCode(AccessCode newSelection)
    {
   //     dc.setManagerMode(PersistMode.UPDATE);
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
        
     
        btnSaveLoc.setOnMouseClicked(e -> {
            dc.saveAccessCode(Integer.valueOf(txtField.getText()));
        });
        
        HBox test = new HBox(txtField, btnSaveLoc);
        test.setPrefWidth(hBoxInfo.getWidth());
        
        
        
        popup.setPopupContent(test);
        
        popup.show(hBoxInfo, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        tabBox.getSelectionModel().selectFirst();
        Box box = (Box) arg;
  //     box.addAction();
      //  List temp = new ArrayList<>();
     //   temp.add(tblAllActions.getItems().get(2));
      //  dc.addToTempList(temp);
       
    
        txtDescription.setText(box.getDescription());
        
        if(box.getName() == null || box.getName().isEmpty())
            txtName.requestFocus();
        
        txtName.setText(box.getName());
        btnSave.setDisable(false);
           
     //   txtName1.setText(box.getName());
       // txtDescription1.setText(box.getDescription());
       
   
    }

     @FXML
    private void saveBox(ActionEvent event)
    { 
     try {
             dc.saveBox(txtName.getText(), txtDescription.getText());
        } catch(IllegalArgumentException e) {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(e.getMessage()));
            JFXButton okButton = new JFXButton("OK");
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
            JFXButton okButton = new JFXButton("OK");
            okButton.setOnMouseClicked(e -> dialog.close());
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
        }
    }

    public void setDialog(JFXDialog dialogLayout) {
        dialog = dialogLayout;
    }

    @FXML
    private void cancel(ActionEvent event)
    {
        dc.setManagerMode(Box.class, PersistMode.NEW);
       resetTextFields();
       btnSave.setDisable(true);
       
    }

    private void resetTextFields() {
         dc.setSelectedItem(new Box());
    }

     @FXML
    private void sendToTemp(ActionEvent event)
    {
        dc.addToTempList(tblAllExercise.getSelectionModel().getSelectedItems());
        if(tblAllExercise.getItems().size() > 0) 
            tblAllExercise.getSelectionModel().select(0);
    }

    @FXML
    private void removeFromTemp(ActionEvent event)
    {
          dc.removeFromTempList(tblSelectedExercise.getSelectionModel().getSelectedItems());
           if(tblSelectedExercise.getItems().size() > 0) 
            tblSelectedExercise.getSelectionModel().select(0);
    }

    private void hackTooltipStartTiming(Tooltip tooltip) {
    try {
        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
        fieldBehavior.setAccessible(true);
        Object objBehavior = fieldBehavior.get(tooltip);

        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
        fieldTimer.setAccessible(true);
        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

        objTimer.getKeyFrames().clear();
        objTimer.getKeyFrames().add(new KeyFrame(new Duration(100)));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
