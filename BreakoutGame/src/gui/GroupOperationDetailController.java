/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import domain.ExerciseDomainController;
import domain.GroupOperation;
import domain.OperationCategory;
import domain.PersistMode;
import gui.ComplexApplication2.ExerciseController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
public class GroupOperationDetailController extends AnchorPane implements Observer {

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

    private JFXDialog dialog;
    public GroupOperationDetailController(ExerciseDomainController dc, JFXDialog dia)
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
        cmbGrouOpSorts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        generateGroupOperationInputHbox();
                      

                    }
        });
        this.dialog = dia;
    }

    @FXML
    private void addNewGroupOp(ActionEvent event)
    {
        dc.setManagerModeGroupOp(PersistMode.NEW);
        dc.setSelectedItem(new GroupOperation());

    }

    @FXML
    private void saveGroupOp(ActionEvent event)
    {
        try {
            dc.saveGroupOperation(cmbGrouOpSorts.getSelectionModel().getSelectedItem(), textFields.stream().map(TextField::getText).collect(Collectors.toList()));
        dc.setManagerMode(GroupOperation.class, PersistMode.UPDATE);
        } catch(IllegalArgumentException ex) {
            setErrorDialog(ex);
        }
        
    }

    private void generateGroupOperationInputHbox()
    {
        // cmbGrouOpSorts.
        hBoxGroupOpContent.setPadding(new Insets(5));
        hBoxGroupOpContent.setStyle("-fx-border-radius: 0.5; -fx-border-color: black; -fx-background-color: lightgrey");
        hBoxGroupOpContent.getChildren().clear();

        List<Node> nodes = new ArrayList<>();
        String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
        String[] description = cmbGrouOpSorts.getSelectionModel().getSelectedItem().getDescription().split(String.format(WITH_DELIMITER, "%s"));

        textFields.clear();
        JFXTextField input;
        Label words;
        for (String string : description)
        {
            if (string.equals("%s"))
            {
                input = new JFXTextField();
                input.setPrefWidth(50);
                //       input.getStyleClass().add("textFieldWhite");
                textFields.add(input);
                nodes.add(input);
                //  hBoxGroupOpContent.getChildren().add(input);
            } else
            {
                words = new Label(string);
                words.setPadding(new Insets(5, 0, 0, 0));
                //     words.getStyleClass().add("textFieldWhite");
                nodes.add(words);
                //      hBoxGroupOpContent.getChildren().add(words);
            }

        }
        hBoxGroupOpContent.getChildren().addAll(nodes);
    }

    @FXML
    private void deleteGroupOp(ActionEvent event)
    {
        try {
                  dc.deleteGroupOperation();
        } catch(IllegalArgumentException e) {
            setErrorDialog(e);
        }
  
        
    }

    @Override
    public void update(Observable o, Object arg)
    {
        GroupOperation groupOperation = (GroupOperation) arg;
        if(groupOperation.getValueString() != null && !groupOperation.getValueString().isEmpty()) {
        cmbGrouOpSorts.getSelectionModel().select(groupOperation.getCategory());
        generateGroupOperationInputHbox();
 
        String[] values = groupOperation.getValueString().split("&");
        IntStream.range(0, values.length).forEach(e -> {
            textFields.get(e).setText(values[e]);
        });             
        }
    }
    
        private void setErrorDialog(Exception ex) {
         JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(ex.getMessage()));
            JFXButton okButton = new JFXButton("OK");
            okButton.setOnMouseClicked(e -> dialog.close());
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
    }

}
