/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.util.Objects;
import javax.persistence.CascadeType;
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
@NamedQueries(@NamedQuery(name="StudentExists", query="SELECT s FROM Student s WHERE s.studentClass.studentClassName=:studentClass AND s.classnumber=:classnumber")
                )
@Entity
@Table(name="Student")
public class Student implements IManageable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String classnumber;

    public Student(String firstName, String lastName,StudentClass studentClass, String classnumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setStudentClass(studentClass);
        setClassNumber(classnumber);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.classnumber);
        hash = 29 * hash + Objects.hashCode(this.studentClass.getStudentClassName());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.classnumber, other.classnumber)) {
            return false;
        }
        if (!this.studentClass.getStudentClassName().equals(other.getStudentClass().getStudentClassName())) {
            return false;
        }
        return true;
    }
    
    //For JPA
    protected Student()
    {
        
    }
    
    @ManyToOne()
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

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass classRoom) {
        this.studentClass = classRoom;
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

