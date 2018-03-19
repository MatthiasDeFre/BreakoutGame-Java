/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ComplexApplication2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import domain.BoxController;
import domain.ExerciseDomainController;
import gui.ExerciseDetailScreenController2;
import gui.ExerciseDetailsPaneMidController;
import gui.ExercisesGroupOperationsPaneRightController;
import gui.ExercisesPaneLeftController;
import gui.FiltersBoxController;
import gui.GroupScreenController;
import gui.NavigationMenuController;
import gui.SessionModifyPaneLeftController;
import gui.SessionModifyPaneRightController;
import gui.StartScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import persistence.PersistenceController;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseController extends StackPane {

    private ExerciseDetailScreenController2 exerciseDetailScreen2;
    private StartScreenController startScreen;
    private GroupScreenController groupScreen;
    private ExercisesPaneLeftController screen1;
    private ExerciseDetailsPaneMidController screen2;
    private ExercisesGroupOperationsPaneRightController screen3;

    private SessionModifyPaneRightController test;
    private SessionModifyPaneLeftController test2;

    private ExerciseDomainController dc;
    @FXML
    private SplitPane splitPane;
    @FXML
    private HBox hBoxNavBar;
    @FXML
    private JFXButton btnFilters;
    @FXML
    private JFXDrawer filterDrawer;
    @FXML
    private JFXDrawer testD;

      private JFXDialog dialogScreen;
    
    private JFXDialogLayout dialogContent;
    
    public ExerciseController(ExerciseDomainController dc) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Exercise.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
       
        dialogScreen = new JFXDialog();
       dialogContent = new JFXDialogLayout();
   
       dialogScreen.setContent(dialogContent);
       dialogScreen.setDialogContainer(this);
        
        this.dc = dc;
        startScreen = new StartScreenController(dc);
        exerciseDetailScreen2 = new ExerciseDetailScreenController2(dc);
        groupScreen = new GroupScreenController(dc);
        screen1 = new ExercisesPaneLeftController(dc, dialogScreen);
        screen2 = new ExerciseDetailsPaneMidController(dc, dialogScreen);
        screen3 = new ExercisesGroupOperationsPaneRightController(dc, dialogScreen);

        //test = new SessionModifyPaneRightController(dc);
        //test2 = new SessionModifyPaneLeftController(dc);

//        testScreen = new TestScreenController(dc);
//        exerciseDetailScreen = new ExerciseDetailScreenController(dc);
//        this.add(startScreen, 0, 0);
//        this.add(testScreen, 1, 0);
        dc.addObserverExercise(screen2);
        dc.addObserverExercise(screen3);

        splitPane.getItems().add(screen1);
        splitPane.getItems().add(screen2);
        splitPane.getItems().add(screen3);
     //   splitPane.getItems().add(test2);
       // splitPane.getItems().add(test);

        String image = ExerciseController.class.getResource("boeken.jpg").toExternalForm();
        splitPane.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");
        
        testD.setSidePane(new NavigationMenuController());
        List<Node> filterActions = new ArrayList<>();
        JFXButton closeFilter = new JFXButton("Sluit filters");
        closeFilter.getStyleClass().add("closeButton");
        closeFilter.setOnAction(e -> {
           filterDrawer.close();
        });
        filterActions.add(closeFilter);
        filterDrawer.setSidePane(new FiltersBoxController(dc, filterActions));
         this.getChildren().remove(testD);
         this.getChildren().remove(filterDrawer);
     //  testD.open();
       testD.setOnDrawerClosed(e -> {
           this.getChildren().remove(testD);
       });
       filterDrawer.setOnDrawerClosed(e -> {
           this.getChildren().remove(filterDrawer);
       });
    
       screen3.setDialog(dialogScreen);
    }

   
    @FXML
    private void test(MouseEvent event)
    {
        this.getChildren().add(testD);
        testD.open();
    }

 
 

    @FXML
    private void applyFilters(ActionEvent event)
    {
           this.getChildren().add(filterDrawer);
        dc.applyFilters();
        filterDrawer.open();
    }


}
