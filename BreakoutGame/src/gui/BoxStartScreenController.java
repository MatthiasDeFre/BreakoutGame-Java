/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import domain.Box;
import domain.BoxController;
import gui.ComplexApplication2.ExerciseController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxStartScreenController  extends StackPane {

    @FXML
    private Color x4;
    @FXML
    private Font x3;

    private BoxController dc;
    @FXML
    private SplitPane mainPane;
    @FXML
    private HBox hBoxNavBar;
    @FXML
    private JFXDrawer testD;
    
    private JFXDialog dialogScreen;
    
    private JFXDialogLayout dialogContent;
    
    public BoxStartScreenController(BoxController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxStartScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        BoxOverViewController boxOverViewController = new BoxOverViewController(dc);
    //    mainPane.getItems().add(new AnchorPane());
        JFXDepthManager.setDepth(hBoxNavBar, 45);
        mainPane.getItems().add(boxOverViewController);
        BoxMaxController boxMax = new BoxMaxController(dc);
        JFXDepthManager.setDepth(boxMax, 2);
        mainPane.getItems().add(boxMax);
     //   mainPane.setDividerPosition(0,50);
      //  SplitPane.setResizableWithParent(boxOverViewController, Boolean.TRUE);
      //BoxAccessActionsController boxAccessActionsController = new BoxAccessActionsController(dc);
      //  dc.addObserverBox(boxAccessActionsController);
     
     //   SplitPane.setResizableWithParent(boxAccessActionsController, Boolean.TRUE);
    
    //   mainPane.getItems().add(boxAccessActionsController);
    //    BoxExerciseListController boxExerciseListController = new BoxExerciseListController(dc);
       //    dc.addObserverBox(boxExerciseListController);
    //   mainPane.getItems().add(boxExerciseListController);
     //  mainPane.setDividerPositions(100, 100,100);
       String image = ExerciseController.class.getResource("boeken.jpg").toExternalForm();
        mainPane.setStyle("-fx-background-image: url('" + image + "'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
     
     dc.addObserverBox(boxMax);
     
     
        testD.setSidePane(new NavigationMenuController());
         this.getChildren().remove(testD);
     //  testD.open();
       testD.setOnDrawerClosed(e -> {
           this.getChildren().remove(testD);
       });

       dialogScreen = new JFXDialog();
       dialogContent = new JFXDialogLayout();
   
       dialogScreen.setContent(dialogContent);
       dialogScreen.setDialogContainer(this);
       
     //  boxAccessActionsController.setDialog(dialogScreen);
     boxMax.setDialog(dialogScreen);
     
       this.requestFocus();
        
    }

    @FXML
    private void test(MouseEvent event)
    {
        this.getChildren().add(testD);
        testD.open();
    }

    
    
}
