/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Student;

/**
 *
 * @author geers
 */
public class StudentRepository extends GenericRepository<Student> {
    
    public StudentRepository()
    {
        this(Student.class);
    }
    
    private StudentRepository(Class<Student> type) {
        super(type);
    }
    
}
