/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.StudentManager;
import persistence.ExcelStudents;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import persistence.JPAUtil;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class ExcelStudentsImport {
    ExcelStudents excelStudents=new ExcelStudents();
    PersistenceController persistenceController=new PersistenceController();
    public void AddStudentsExcel(String bestandsNaam)
    {
        List<String> listExcelDataStudents=excelStudents.ImportStudents(bestandsNaam);
        ListIterator<String> Iterator=listExcelDataStudents.listIterator();
        
        //Klas opvragen uit excel,deze zit vooraan in de lijst
        String klas=Iterator.next();
        List<Student> listStudents= new ArrayList<>();
        while(Iterator.hasNext())
        {
            Student student=new Student(Iterator.next(),Iterator.next(),klas,Iterator.next());
            listStudents.add(student);
        }
        
        //Lijst omzetten naar een ListIterator
        ListIterator<Student>lijstIterator2=listStudents.listIterator();
        EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        
        while(lijstIterator2.hasNext())
        {
            Student student=lijstIterator2.next();
            if(!persistenceController.StudentExists(student.getClassRoom(), student.getClassNumber())==true)
                entityManager.persist(student);
            
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        

        
    }
}
