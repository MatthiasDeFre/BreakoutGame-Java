
import domain.Category;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.PersistMode;
import domain.ExerciseDomainController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistence.JPAUtil;

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
        ExerciseDomainController uc = new ExerciseDomainController();
          EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
         Category math=new Category("MATH");
        Category dutch=new Category("DUTCH");
        Exercise ex = new Exercise("nee", "nee", "nee", "nee", math);
        em.getTransaction().begin();
        em.persist(math);
        em.persist(dutch);
        em.getTransaction().commit();
        uc.setManagerMode(PersistMode.NEW);
        uc.setExercise(new Exercise());
        
        uc.saveExercise(ex);
        ex.setId(1);
        uc.setManagerMode(PersistMode.UPDATE);
        uc.setExercise(ex);
     
        Exercise exA = new Exercise("Teest", "test", "teest", "test", dutch);
        
        uc.saveExercise(exA);
        System.out.println(ex.toString());
        uc.deleteExercise();
        System.out.println(exA.toString());
        
    }
    
}
