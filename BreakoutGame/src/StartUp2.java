
import domain.Category;
import domain.Exercise;
import domain.PersistMode;
import domain.UseCaseExerciseAdminController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthias
 */
public class StartUp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        UseCaseExerciseAdminController uc = new UseCaseExerciseAdminController();
        Exercise ex = new Exercise("nee", "nee", "nee", "nee", new Category("MATH"));
        uc.setManagerMode(PersistMode.NEW);
        uc.setExercise(new Exercise());
        
        uc.createExercise(ex);
        ex.setId(1);
        uc.setManagerMode(PersistMode.UPDATE);
        uc.setExercise(ex);
     
        Exercise exA = new Exercise("Teest", "test", "teest", "test", new Category("DUTCH"));
        
        uc.createExercise(exA);
        System.out.println(ex.toString());
        
        System.out.println(exA.toString());
        
    }
    
}
