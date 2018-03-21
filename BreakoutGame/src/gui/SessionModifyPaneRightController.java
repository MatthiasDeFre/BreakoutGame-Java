/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import domain.BoBGroup;
import domain.Box;
import domain.Category;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.PersistMode;
import domain.Session;
import domain.SessionController;
import domain.Student;
import domain.StudentClass;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneRightController extends AnchorPane implements Observer {

    private SessionController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtClass;
    @FXML
    private JFXDatePicker DPDate;
    @FXML
    private JFXToggleButton TBFeedback;
    @FXML
    private JFXToggleButton TBTile;
    @FXML
    private TableView<Box> tblBOBS;
    @FXML
    private TableColumn<Box, String> clmBob;
    @FXML
    private JFXTextField txtNaamBox;
    @FXML
    private TableView<Goal> tblGoal;
    @FXML
    private TableColumn<Goal, String> clmGoal;
    @FXML
    private JFXToggleButton TBGroups;
    @FXML
    private JFXButton btnGenerateGroup;
    @FXML
    private JFXTextField txfGroupAmount;
    @FXML
    private TableView<BoBGroup> tblGroups;
    @FXML
    private TableColumn<BoBGroup, String> clmGroups;
    @FXML
    private TableView<Student> tblGrouplessStudents;
    @FXML
    private TableColumn<Student, String> clmGrouplessStudents;
    @FXML
    private JFXButton btnNewGroup;
    @FXML
    private HBox hboxGroupEdit;
    @FXML
    private JFXButton btnStudent;
    @FXML
    private JFXComboBox<StudentClass> cmbClass;
    @FXML
    private JFXButton btnOpslaan;
    @FXML
    private JFXButton btnDeleteStudent;
    @FXML
    private JFXButton btnGeneratePaths;

    public SessionModifyPaneRightController(SessionController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneRight.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        clmBob.setCellValueFactory(e -> e.getValue().nameProperty());
        clmGoal.setCellValueFactory(e -> e.getValue().code());
        tblBOBS.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
            }
        });
         cmbClass.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
            }
        });
        tblGroups.setItems(dc.getGroupTempList());
        clmGroups.setCellValueFactory(e -> e.getValue().nameProperty());
        tblGroups.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
                
            }
        });
       
        cmbClass.setItems(dc.getFilteredItems(StudentClass.class));
        StringConverter<StudentClass> converter = new StringConverter<StudentClass>() {
            @Override
            public String toString(StudentClass classRoom) {
                return classRoom.getStudentClassName();
            }
            @Override
            public StudentClass fromString(String string)
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
     
        tblGrouplessStudents.setItems(dc.getStudentsFromClass());
        cmbClass.setConverter(converter);
        GroupEditDetailController groupEditDetailController = new GroupEditDetailController(dc);
        dc.addObserver(BoBGroup.class, groupEditDetailController);
        hboxGroupEdit.getChildren().add(groupEditDetailController);
        clmGrouplessStudents.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName() + " " + e.getValue().getLastName()));
    }

    @Override
    public void update(Observable o, Object obj) {
        if (obj instanceof Session) {
            Session session = (Session) obj;
            txtName.setText(session.getName());
            txtDescription.setText(session.getDescription());
            if(session.getClassRoom() != null) 
                txtClass.setText(session.getClassRoom().getStudentClassName());
            else
                txtClass.setText("");
            DPDate.setValue(session.getActivationDate());
            TBFeedback.setSelected(session.isFeedback());
            TBTile.setSelected(session.isTile());
            ObservableList bobs = dc.getFilteredItems(Box.class);
            tblBOBS.setItems(bobs);
            tblBOBS.getSelectionModel().select(session.getBox());
            
        }

        if (obj instanceof Box) {
            ObservableList goals = dc.getGoals();
            tblGoal.setItems(goals);
            txtNaamBox.setText(((Box) obj).getName());
        }
        if (obj instanceof StudentClass) {
            System.out.println("test");
            StudentClass studentClass = (StudentClass) obj;
            dc.setStudents(studentClass.getStudents());
        }

    }

    @FXML
    private void generateGroups() {
        int amount;
        
        if (TBTile.isSelected()) {
            amount =cmbClass.getSelectionModel().getSelectedItem().getStudents().size();
        } else {
            amount = Integer.valueOf(txfGroupAmount.getText());
        }

        dc.generateGroups(amount, TBGroups.isSelected(), cmbClass.getSelectionModel().getSelectedItem());
    }

    private void generatePaths() {

    }

    @FXML
    private void btnStudentOnAction(ActionEvent event) {
        dc.addStudentToTempGroup(tblGrouplessStudents.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void btnOpslaanOnAction(ActionEvent event) {
        dc.saveSession(txtDescription.getText(), txtName.getText(), DPDate.getValue(), TBTile.isSelected(), TBFeedback.isSelected(), cmbClass.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void btnDeleteStudentOnAction(ActionEvent event) {
    }

    @FXML
    private void btnGeneratePathsOnAction(ActionEvent event) {
    }
}
