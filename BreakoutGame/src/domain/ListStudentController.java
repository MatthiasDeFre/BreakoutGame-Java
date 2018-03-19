/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.StudentManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class ListStudentController{
    private StudentManager studentManager;
    private PersistenceController persistenceController;
    ExcelStudentsImport excelStudentsImport;
  
    public ListStudentController() {
        this.persistenceController = new PersistenceController();
        this.studentManager = new StudentManager(persistenceController);
        this.excelStudentsImport=new ExcelStudentsImport();
    }

    public void setManagerMode(PersistMode persistMode) {
        persistenceController.setPersistMode(persistMode);
    }
    
     public List<Student> getListAllStudents()
    {
        return persistenceController.getAllOfType(Student.class);
    }
     
     public void createStudent(Student student) {
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

    public void ImportStudentsExcel() {
        excelStudentsImport.AddStudentsExcel();
    }
    
//    public void getExcelStudentsObject()
//    {
//        List<String> listStudentsExcel=excelStudentsImport.AddStudentsExcel();
//        ListIterator<String> lijstIterator=listStudentsExcel.listIterator();
//        List<Student> listStudents= new ArrayList<>();
//        //klasNummer opvragen
//        String klas=lijstIterator.next();
//        
//        while(lijstIterator.hasNext())
//        {
//            Student student=new Student(lijstIterator.next(),lijstIterator.next(),klas,lijstIterator.next());
//            listStudents.add(student);
//        }
//        
//        //Lijst omzetten naar een ListIterator
//        ListIterator<Student> lijstIterator2;
//        lijstIterator2= listStudents.listIterator();
//        while(lijstIterator2.hasNext())
//        {
//            studentManager.save(lijstIterator2.next());
//        }
//    }

}
