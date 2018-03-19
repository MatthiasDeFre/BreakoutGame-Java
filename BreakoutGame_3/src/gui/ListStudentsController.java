/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import domain.Exercise;
import domain.ListStudentController;
import domain.Student;
import domain.ExerciseDomainController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author geers
 */
public class ListStudentsController extends AnchorPane {

    private ListStudentController dc;
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
    
    public ListStudentsController(ListStudentController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListStudents.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }
        this.dc = dc;
        listStudents = FXCollections.observableArrayList(dc.getListAllStudents());
        tblStudents.setItems(listStudents);
        clmFirstName.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getFirstName()));
        clmLastName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLastName()));
        clmClassroom.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getClassRoom()));
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
                    student = new Student(txtFirstName.getText(),txtLastName.getText(),txtClassroom.getText(),txtClassnumber.getText());
                    dc.createStudent(student);
        }catch(Exception ex)
        {
            System.out.printf("%s%n",ex.toString());
        }
        refreshTableView();

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
            tblStudents.getSelectionModel().getSelectedIndex();
            Student student=new Student(tblStudents.getSelectionModel().getSelectedItem().getFirstName(),
                    tblStudents.getSelectionModel().getSelectedItem().getLastName(),
                    tblStudents.getSelectionModel().getSelectedItem().getClassRoom(),
                    tblStudents.getSelectionModel().getSelectedItem().getClassNumber());
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
        dc.ImportStudentsExcel();
        refreshTableView();
    }
    
    public void refreshTableView()
    {   
        listStudents = FXCollections.observableArrayList(dc.getListAllStudents());
        tblStudents.setItems(listStudents);
    }
    
}
