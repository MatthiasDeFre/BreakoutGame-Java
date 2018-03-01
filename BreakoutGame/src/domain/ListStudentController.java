/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.StudentManager;
import java.util.List;
import java.util.Observable;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class ListStudentController extends Observable{
    private StudentManager studentManager;
    private PersistenceController persistenceController;

    public ListStudentController() {
        this.persistenceController = new PersistenceController();
        this.studentManager = new StudentManager(persistenceController);
        
    }

    
    public void createUser(Student student)
    {
        studentManager.addStudent(student);
    }
    
     public List<Student> getListAllStudents()
    {
        return persistenceController.getAllOfType(Student.class);
    }
     
     public void createExercise(Student student) {
        studentManager.addStudent(student);
    }
     
    public void setStudent(Student student)
    {
        studentManager.setSelected(student);
    }
}
