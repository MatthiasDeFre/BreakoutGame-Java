/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author geers
 */
public class StudentTest {
    public StudentTest()
    {
        
    }
    
    @Test
    public void correctConstructor()
    {
        Student student= new Student("Jelle","Geers");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void emptyFirstNameThrowsException()
    {
        Student student= new Student("","Geers");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void emptyLastNameThrowsException()
    {
        Student student= new Student("Jelle","");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nullLastNameThrowsException()
    {
        Student student= new Student("Jelle",null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nullFirstNameThrowsException()
    {
        Student student= new Student(null,"");
    }
}
