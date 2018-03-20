/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author geers
 */
@NamedQueries(@NamedQuery(name="StudentExists", query="SELECT s FROM Student s WHERE s.classroom=:classroom AND s.classnumber=:classnumber")
                )
@Entity
@Table(name="Student")
public class Student implements IManageable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String classroom;
    private String classnumber;

    public Student(String firstName, String lastName,String classroom, String classnumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setClassRoom(classroom);
        setClassNumber(classnumber);
    }
    
    //For JPA
    protected Student()
    {
        
    }
    
    @ManyToOne
    private StudentClass studentClass;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName==null ||firstName.trim().equals(""))
            throw new IllegalArgumentException();
        else
            this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName==null||lastName.trim().equals(""))
            throw new IllegalArgumentException();
        else
            this.lastName = lastName;
    }

    public String getClassRoom() {
        return classroom;
    }

    public void setClassRoom(String classRoom) {
        this.classroom = classRoom;
    }

    public String getClassNumber() {
        return classnumber;
    }

    public void setClassNumber(String classNumber) {
        this.classnumber = classNumber;
    }
    

    @Override
    public String toString() {
        return String.format("%s %s ", this.firstName,this.lastName);
    }

    public void copy(Student student) {
        setFirstName(student.getFirstName());
        setLastName(student.getLastName());
    }
    
    
    
    
    
    
}

