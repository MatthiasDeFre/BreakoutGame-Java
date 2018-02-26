/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author geers
 */
@Entity
@Table(name="StudentClass")
public class StudentClass {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String studentClassName;
    
    @OneToMany
    private Set<Student> students= new HashSet<>();

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public StudentClass() {
    }

    public StudentClass(String StudentClassName) {
        this.studentClassName = StudentClassName;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        if(studentClassName==null|studentClassName.trim().equals(""))
            throw new IllegalArgumentException();
        else
            this.studentClassName=studentClassName;
    }
    
    
    
    
}
