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
public class ListStudentController{
    private StudentManager studentManager;
    private PersistenceController persistenceController;
  
    public ListStudentController() {
        this.persistenceController = new PersistenceController();
        this.studentManager = new StudentManager(persistenceController);
        
    }

    public void setManagerMode(PersistMode persistMode) {
        persistenceController.setPersistMode(persistMode);
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
    
    public void deleteStudent(Student student)
    {
        studentManager.delete();
    }

    public void saveStudent(String voornaam, String achternaam) {
        studentManager.save(new Student(voornaam,achternaam));
    }

}
