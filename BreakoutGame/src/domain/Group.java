/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geers
 */
public class Group implements IManageable {

    
    private String name;
    private List<Student> students;
    private Path path;

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
        System.out.println(path.getToStringTest());
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
}
