/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.effects.JFXDepthManager;
import domain.Box;
import domain.BoxController;
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
     //   mainPane.setDividerPosition(0,50);
      //  SplitPane.setResizableWithParent(boxOverViewController, Boolean.TRUE);
        BoxAccessActionsController boxAccessActionsController = new BoxAccessActionsController(dc);
        dc.addObserverBox(boxAccessActionsController);
     
     //   SplitPane.setResizableWithParent(boxAccessActionsController, Boolean.TRUE);
    
       mainPane.getItems().add(boxAccessActionsController);
        BoxExerciseListController boxExerciseListController = new BoxExerciseListController(dc);
           dc.addObserverBox(boxExerciseListController);
       mainPane.getItems().add(boxExerciseListController);
     //  mainPane.setDividerPositions(100, 100,100);
        testD.setSidePane(new NavigationMenuController());
       testD.open();
       testD.setOnDrawerClosed(e -> {
           this.getChildren().remove(testD);
       });
    //   testD.close();
       //  this.getChildren().remove(testD);
       this.requestFocus();
        
    }

    @FXML
    private void test(MouseEvent event)
    {
        this.getChildren().add(testD);
        testD.open();
    }

    
    
}
