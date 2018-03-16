/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domain.ExerciseDomainController;
import domain.GroupOperation;
import domain.OperationCategory;
import domain.PersistMode;
import gui.ComplexApplication2.ExerciseController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class GroupOperationDetailController extends AnchorPane{

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private HBox hBoxGroupOpContent;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXComboBox<OperationCategory> cmbGrouOpSorts;

    private List<TextField> textFields;
    
    private ExerciseDomainController dc;
    
    public GroupOperationDetailController(ExerciseDomainController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupOperationDetail.fxml"));
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
        textFields = new ArrayList<>();
        //cmbGrouOpSorts.setItems(FXCollections.observableArrayList(Arrays.stream(OperationCategory.values()).map(e -> e.getSort()).collect(Collectors.toList())));
        cmbGrouOpSorts.setItems(FXCollections.observableArrayList(OperationCategory.values()));
        cmbGrouOpSorts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if (newSelection != null)
                    {
                        hBoxGroupOpContent.setPadding(new Insets(5));
                        hBoxGroupOpContent.setStyle("-fx-border-radius: 0.5; -fx-border-color: black; -fx-background-color: lightgrey");
                        hBoxGroupOpContent.getChildren().clear();
                        hBoxGroupOpContent.getChildren().addAll(getGroupOperationInput());
                       
                    
                    }
        });
    }
    @FXML
    private void addNewGroupOp(ActionEvent event)
    {
        dc.setManagerModeGroupOp(PersistMode.NEW);
        dc.setGroupOperation(new GroupOperation());
        
    }

    @FXML
    private void saveGroupOp(ActionEvent event)
    {
        dc.saveGroupOperation(cmbGrouOpSorts.getSelectionModel().getSelectedItem(), textFields.stream().map(TextField::getText).collect(Collectors.toList()));
    }
    
    private List<Node> getGroupOperationInput() {
       // cmbGrouOpSorts.
       List<Node> nodes = new ArrayList<>();
        String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
       String[] description = cmbGrouOpSorts.getSelectionModel().getSelectedItem().getDescription().split(String.format(WITH_DELIMITER, "%s"));
       
        textFields.clear();
        JFXTextField input;
        Label words;
       for (String string : description)
        {
            if(string.equals("%s")) {
                input = new JFXTextField();
                input.setPrefWidth(50);
         //       input.getStyleClass().add("textFieldWhite");
                textFields.add(input);
                nodes.add(input);
              //  hBoxGroupOpContent.getChildren().add(input);
            } else {
               words= new Label(string);
               words.setPadding(new Insets(5, 0, 0, 0));
           //     words.getStyleClass().add("textFieldWhite");
               nodes.add(words);
         //      hBoxGroupOpContent.getChildren().add(words);
            }
               
        }
       return nodes;
    }

    @FXML
    private void deleteGroupOp(ActionEvent event)
    {
        dc.deleteGroupOperation();
    }
    
}