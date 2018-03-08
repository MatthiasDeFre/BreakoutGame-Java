/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domain.Category;
import domain.Exercise;
import domain.ExerciseDomainController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseDetailsPaneMidController extends AnchorPane implements Observer {

    @FXML
    private JFXTextField txtAnw;
    @FXML
    private JFXComboBox<Category> cmbCategory;
    @FXML
    private JFXTextField txtEx;
    @FXML
    private JFXButton btnSave;

    private ExerciseDomainController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txfFeedback;
    @FXML
    private JFXButton btnFeedback;
    @FXML
    private JFXTextField txfOpdracht;
    @FXML
    private JFXButton btnAssignment;

    public ExerciseDetailsPaneMidController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExerciseDetailsPaneMid.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

//        dc.addObserver(this);
        this.dc = dc;
        cmbCategory.setItems(dc.getCategories());
        StringConverter<Category> converter = new StringConverter<Category>() {
            @Override
            public String toString(Category category) {
                return category.getDescription();
            }

            @Override
            public Category fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        cmbCategory.setConverter(converter);
        cmbCategory.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {
            System.out.println(cmbCategory.getValue().getDescription());
        });

    }


    @Override
    public void update(Observable o, Object obj) {
        Exercise exercise = (Exercise) obj;
        txtAnw.setText(exercise.getAnswer());
        txtEx.setText(exercise.getName());
        //    txtCat.setText(exercise.getCategoryDescription());
        cmbCategory.getSelectionModel().select(exercise.getCategory());
        txfOpdracht.setText(exercise.getAssignment());
        txfFeedback.setText(exercise.getFeedback());
        //     tblViewGroupOperations.setItems(exercise.getGroupOperationsObservableList()); //deze gebruiken
        //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
        //  clmValue.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDescription()))); //deze gebruiken

    }

    @FXML
    private void btnFeedbackOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*.pdf"));

        File selectedFile = fileChooser.showOpenDialog(new Stage()); //moet nog aangepast worden
        txfFeedback.setText(selectedFile.getPath());
    }

    @FXML
    private void btnAssignmentOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*.pdf"));

        File selectedFile = fileChooser.showOpenDialog(new Stage()); //moet nog aangepast worden
        txfOpdracht.setText(selectedFile.getPath());
    }

    @FXML
    private void btnSaveExOnAction(ActionEvent event)
    {
        dc.saveExercise(txtEx.getText(), txtAnw.getText(), txfFeedback.getText(), txfOpdracht.getText(), cmbCategory.getSelectionModel().getSelectedIndex());
    }

}
