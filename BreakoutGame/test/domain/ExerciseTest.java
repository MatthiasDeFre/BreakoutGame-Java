/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

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
    @Test 
    public void correctConstructor() {
        
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
    
}
