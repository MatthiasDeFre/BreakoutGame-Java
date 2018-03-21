/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domain.Classroom;
import domain.Exercise;
import domain.ListStudentController;
import domain.Student;
import domain.ExerciseDomainController;
import domain.PersistMode;
import domain.StudentClass;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author geers
 */
public class ListStudentsController extends AnchorPane {

    private ListStudentController dc;
    String bestandsNaam;
    @FXML
    private TableView<Student> tblStudents;
    private ObservableList<Student> listStudents;
    @FXML
    private TableColumn<Student, String> clmLastName;
    @FXML
    private TableColumn<Student, String> clmFirstName;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnModify;
    private Button btnSave;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private TableColumn<Student, String> clmClassroom;
    @FXML
    private TableColumn<Student, String> clmClassnumber;
    @FXML
    private JFXTextField txtClassroom;
    @FXML
    private JFXTextField txtClassnumber;
    @FXML
    private Button btnStudentsImport;
    @FXML
    private JFXTextField txtBestandsNaam;
    @FXML
    private JFXButton btnExcelBestand;
    @FXML
    private AnchorPane anchorPane;
    
    public ListStudentsController(ListStudentController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListStudents.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }
        String image = ExerciseController.class.getResource("boeken.jpg").toExternalForm();
        anchorPane.setStyle("-fx-background-image: url('" + image + "'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
        this.dc = dc;
        listStudents = FXCollections.observableArrayList(dc.getListAllStudents());
        //listStudents = dc.getStudents();
        tblStudents.setItems(listStudents);
        clmFirstName.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getFirstName()));
        clmLastName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLastName()));
        clmClassroom.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getStudentClass().getStudentClassName()));
        clmClassnumber.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getClassNumber()));
        btnRemove.setOnAction(this::btnRemoveStudent);
        tblStudents.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection != null)
                setStudentToDC();
        });
        
    }
    
    @FXML
    private void btnAddStudent(ActionEvent event) {
        Student student;
        try{
            dc.setManagerMode(PersistMode.NEW);
                    student = new Student(txtFirstName.getText(),txtLastName.getText(),new StudentClass(txtClassroom.getText()),txtClassnumber.getText());
                    dc.createStudent(student);
                     refreshTableView();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtClassnumber.setText("");
        txtClassroom.setText("");
        }catch(Exception ex)
        {
            System.out.printf("%s%n",ex.toString());
        }
       

//        dc.setStudent(student);
//        listStudents.add(student);
////        Student student= new Student(txtVoornaam.getText(),txtAchternaam.getText());
////        dc.setStudent(student);
////        listStudents.add(student);
////        dc.createUser(student);
//        System.out.printf("%s %s is added %n",student.getFirstName(),student.getLastName());
//        
    }
    
    @FXML
    private void btnRemoveStudent(ActionEvent event)
    {
        dc.removeStudent();
            tblStudents.getSelectionModel().getSelectedIndex();
            refreshTableView();
      /*      Student student=new Student(tblStudents.getSelectionModel().getSelectedItem().getFirstName(),
                    tblStudents.getSelectionModel().getSelectedItem().getLastName(),
                    tblStudents.getSelectionModel().getSelectedItem().getClassRoom(),
                    tblStudents.getSelectionModel().getSelectedItem().getClassNumber());*/

//        System.out.printf("%s %s is verwijderd",student.getFirstName(),student.getLastName());
//        dc.removeUser(student);
//        dc.setManagerMode(PersistMode.REMOVE);
//        Student student= tblStudents.getSelectionModel().getSelectedItem();
//        System.out.printf("eeeeee %s",student.getFirstName());
//        listStudents.remove(student);
//        dc.deleteStudent(student);
    
    }

    @FXML
    private void btnModifyStudent(ActionEvent event) {
//        dc.setManagerMode(PersistMode.UPDATE);
//        Student student= new Student(txtVoornaam.getText(),txtAchternaam.getText());
        
    }
    
    private void setStudentToDC()
    {
        dc.setManagerMode(PersistMode.UPDATE);
        dc.setStudent(tblStudents.getSelectionModel().getSelectedItem());
    }
    
    public void update(Observable o, Object obj)
    {
        Student student=(Student) obj;
        
    }

    private void btnSaveStudent(ActionEvent event) {
//         Student student=new Student(txtVoornaam.getText(),txtAchternaam.getText());
//         dc.saveStudent(txtVoornaam.getText(),txtAchternaam.getText());
//         listStudents.add(student);
    }

    @FXML
    private void btnStudentsImportExcel(ActionEvent event) {
        dc.ImportStudentsExcel(bestandsNaam);
        refreshTableView();
//        btnStudentsImport.setDisable(true);
    }
    
    public void refreshTableView()
    {   
       listStudents = FXCollections.observableArrayList(dc.getListAllStudents());
        tblStudents.setItems(listStudents);
    }

    @FXML
    private void btnExcelFileOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(SceneController4.getStage());
        bestandsNaam=selectedFile.getPath();
        txtBestandsNaam.setText(selectedFile.getPath());
        btnStudentsImport.disableProperty().bind(txtBestandsNaam.textProperty().isEmpty());
                
    }
    
}
