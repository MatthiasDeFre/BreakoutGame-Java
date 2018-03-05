/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Exercise;
import domain.ListStudentController;
import domain.Student;
import domain.ExerciseDomainController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author geers
 */
public class ListStudentsController extends GridPane {

    private ListStudentController dc;
    @FXML
    private TableView<Student> tblStudents;
    private ObservableList<Student> listStudents;
    @FXML
    private TableColumn<Student, String> clmLastName;
    @FXML
    private TableColumn<Student, String> clmFirstName;
    private TextField lblVoornaam;
    private TextField lblAchternaam;
    @FXML
    private TextField txtVoornaam;
    @FXML
    private TextField txtAchternaam;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnModify;
    
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
        btnRemove.setOnAction(this::btnRemoveStudent);
        btnAdd.setOnAction(this::btnAddStudent);
        btnModify.setOnAction(this::btnModifyStudent);
        tblStudents.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(newSelection != null)
                setStudentToDC();
        });
    }
    
    @FXML
    private void btnAddStudent(ActionEvent event) {
        dc.setManagerMode(PersistMode.NEW);
        Student student= new Student(txtVoornaam.getText(),txtAchternaam.getText());
        dc.setStudent(student);
        listStudents.add(student);
//        Student student= new Student(txtVoornaam.getText(),txtAchternaam.getText());
//        dc.setStudent(student);
//        listStudents.add(student);
//        dc.createUser(student);
        System.out.printf("%s %s is added %n",student.getFirstName(),student.getLastName());
    }
    
    @FXML
    private void btnRemoveStudent(ActionEvent event)
    {
        //tblStudents.getSelectionModel().getSelectedIndex();
//        Student student=new Student(tblStudents.getSelectionModel().getSelectedItem().getFirstName(),tblStudents.getSelectionModel().getSelectedItem().getLastName());
//        System.out.printf("%s %s is verwijderd",student.getFirstName(),student.getLastName());
//        dc.removeUser(student);
        dc.setManagerMode(PersistMode.REMOVE);
        Student student= tblStudents.getSelectionModel().getSelectedItem();
        System.out.printf("eeeeee %s",student.getFirstName());
        listStudents.remove(student);
        dc.deleteStudent(student);
    }

    @FXML
    private void btnModifyStudent(ActionEvent event) {
        dc.setManagerMode(PersistMode.UPDATE);
        Student student= new Student(txtVoornaam.getText(),txtAchternaam.getText());
        
    }
    
    private void setStudentToDC()
    {
        dc.setManagerMode(PersistMode.UPDATE);
        dc.setStudent(tblStudents.getSelectionModel().getSelectedItem());
    }
    
}
