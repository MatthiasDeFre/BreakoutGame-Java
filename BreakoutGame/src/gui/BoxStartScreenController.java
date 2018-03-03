/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxStartScreenController  extends VBox {

    @FXML
    private Color x4;
    @FXML
    private Font x3;

    private BoxController dc;
    @FXML
    private SplitPane mainPane;
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
       
       this.requestFocus();
        
    }

    
    
}
