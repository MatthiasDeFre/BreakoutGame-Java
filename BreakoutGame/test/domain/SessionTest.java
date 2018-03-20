/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Matthias
 */
public class SessionTest {
    
       @Test(expected=IllegalArgumentException.class)
       public void nullNameThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), "TEST", new StudentClass("TEST"), null, LocalDate.MIN, true, true);
       }
         @Test(expected=IllegalArgumentException.class)
       public void emptyNameThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), "TEST", new StudentClass("TEST"), "    ", LocalDate.MIN, true, true);
       }
        @Test(expected=IllegalArgumentException.class)
       public void nullDescriptionThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), null, new StudentClass("TEST"), "TEST", LocalDate.MIN, true, true);
       }
         @Test(expected=IllegalArgumentException.class)
       public void emptyDescriptionThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), "   ", new StudentClass("TEST"), "TEST", LocalDate.MIN, true, true);
       }
       
       
}
