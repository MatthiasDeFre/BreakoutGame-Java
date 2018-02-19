/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import persistence.JPAUtil;

/**
 *
 * @author Matthias
 */
public class ExerciseTest {
    
    public ExerciseTest()
    {
    }
    
    @Before
    public void setUp()
    {
    }

    @Test
    public void testSomeMethod()
    {
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }
    
    // <editor-fold defaultstate="collapsed" desc="Normal case tests">
    @Test 
    public void correctConstructor() {
           Exercise ex = new Exercise("blabla", "5", "DOET IETS", Category.MATH);
           //Assert with all getters
    }
    @Test
    public void emptyOrNullFeedbackNoFeedback() {
        Exercise ex = new Exercise("blabla", "", "DOET IETS", Category.MATH);
        //Assert.assertFalse(ex.hasFeedback());
        Exercise ex2 = new Exercise("blabla", null, "DOET IETS", Category.MATH); 
        //Assert.assertFalse(ex.hasFeedback());
           Exercise ex3 = new Exercise("blabla", "       ", "DOET IETS", Category.MATH); 
        //Assert.assertFalse(ex.hasFeedback());
        
    }
    @Test
    public void notEmptyFeedBackHasFeedback() {
        Exercise ex = new Exercise("blabla", "dddd", "DOET IETS", Category.MATH);
         //Assert.assertTrue(ex.hasFeedback());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tests that throw an exception">
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAnwserThrowsException() {
        Exercise ex = new Exercise(null, "", "doe iets", Category.MATH);
    }
    @Test(expected = IllegalArgumentException.class)
    public void emptyAnwserThrowsException() {
        Exercise ex = new Exercise("   ", "", "doe iets", Category.MATH);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyQuestionThrowsException() {
        Exercise ex = new Exercise("hallo", "", "   ", Category.MATH);
    }
    @Test(expected = IllegalArgumentException.class)
    public void nullQuestionThrowsException() {
        Exercise ex = new Exercise("hallo", "", null, Category.MATH);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCategoryThrowsException() {
        Exercise ex = new Exercise("hallo", "", "hdhshddh", null);
    }
    // </editor-fold>
    
    
}
