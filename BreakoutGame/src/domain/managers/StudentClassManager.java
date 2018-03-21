/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.Session;
import domain.StudentClass;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class StudentClassManager extends Manager<StudentClass>{
    
    private ObservableList students;
    
    protected StudentClassManager()
    {
         this(new PersistenceController());
    }
    public StudentClassManager(PersistenceController persistence)
    {
        super(StudentClass.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(StudentClass.class)));
        students = FXCollections.observableArrayList();
       
    }

    @Override
    public void setSelected(StudentClass item)
    {
        super.setSelected(item); //To change body of generated methods, choose Tools | Templates.
        students.setAll(item.getStudents());
    }
    
    public ObservableList getStudents() {
        return students;
    }
    
    
    
}
