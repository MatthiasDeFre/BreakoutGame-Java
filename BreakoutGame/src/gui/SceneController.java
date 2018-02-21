/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import java.util.Locale;
//import java.util.ResourceBundle;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Alexander
 */
public class SceneController {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    public SceneController() {

    }

    public SceneController(Scene main) {
        this.main = main;
    }

    protected void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name) {
        screenMap.remove(name);
    }

    protected void activate(String name) {
        main.setRoot(screenMap.get(name));
    }

    public void changeScene(String fxml) {
        Stage primaryStage = null;
        
        try {
            Parent pane = FXMLLoader.load(
                    getClass().getResource(fxml));
            
            primaryStage.getScene().setRoot(pane);
        } catch (IOException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



//    public SceneController() {
//        super();//mario
//
//    }
//
//    //scherm toevoegen aan lijst
//    public void addScreen(String name, Node screen) {
//        screens.put(name, screen);
//    }
//
//    public Node getScreen(String name) {
//        return screens.get(name);
//    }
//scherm laden in cache
//    public boolean loadScreen(String name, String resource) {
//
//        try {
//
//            //loader
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
//            System.out.println(getClass().getResource(name));
//            System.out.println("loader");
////            loader.setResources(rs);
////            System.out.println("resources");
//
//            Parent loadScreen = (Parent) loader.load();
//            System.out.println("loadscreen");
//            _Scene sceneControler = ((_Scene) loader.getController());
//            System.out.println("controller");
//            sceneControler.setScreenParent(this);
//            addScreen(name, loadScreen);
//
//            //
//            return true;
//
//        } catch (Exception e) {
//
//            System.out.println(e.getMessage());
//
//            return false;
//        }
//    }
//
//    //Methode om scene te veranderen (met fade)
//    public boolean setScreen(final String name) {
//
//        System.out.println(name);
//        if (screens.get(name) != null) {   //screen loaded
//
//            final DoubleProperty opacity = opacityProperty();
//
//            if (!getChildren().isEmpty()) {
//
//                Timeline fade = new Timeline(
//                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
//                        //fade bij klik
//                        new KeyFrame(new Duration(500), new EventHandler<ActionEvent>() {   //500
//
//                            @Override
//
//                            public void handle(ActionEvent t) {
//
//                                getChildren().remove(0);
//                                getChildren().add(0, screens.get(name));     //toevoeg
//
//                                Timeline fadeIn = new Timeline(
//                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
//                                        //fade na klik
//                                        new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));   //500
//                                fadeIn.play();
//                            }
//                        }, new KeyValue(opacity, 0.0)));
//
//                fade.play();
//
//            } else {
//
//                setOpacity(0.0);
//                getChildren().add(screens.get(name));
//
//                Timeline fadeIn = new Timeline(
//                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
//                        //bij start fade dur
//                        new KeyFrame(new Duration(750), new KeyValue(opacity, 1.0))); //1000v
//
//                fadeIn.play();
//            }
//
//            return true;
//
//        } else {
//
//            System.out.println("test");
//            return false;
//        }
//
//    }
    public boolean switchScene(MouseEvent event, String FXML) {
        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource(FXML)); // ,ResourceBundle.getBundle("lang/Lang", Locale.getDefault())
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (Exception e) {
            System.out.printf("Error switching scene");
        }
        return true;
    }
}