/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author geers
 */
@Entity(name = "SessionGroup")
public class Group implements IManageable, Serializable {

    
    private String name;
    private List<Student> students;
    
   @OneToOne(cascade = CascadeType.ALL)
    private Path path;
    @Id
    private long id;

    public Group()
    {
    }

    public Group(String name)
    {
        this(name, new ArrayList<>());
    }

    public Group(String name, List<Student> students)
    {
        this.name = name;
        this.students = students;
        
    }
    
    
    
    public void setPath(Path path) {
        this.path = path;
    }
    
    @Override
    public long getId()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(long id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void testToString() {
   //     System.out.println(path.getToStringTest());
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(Student student) {
        students.remove(student);
    }
}
