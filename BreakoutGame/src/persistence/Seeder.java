/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Category;
import domain.Exercise;
import domain.GroupOperation;
import domain.OperationCategory;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Matthias
 */
public class Seeder {
    public static void seedDatabaseWithStartData() {
        
        GroupOperation[] goArray = { new GroupOperation(OperationCategory.MULTIPLY, 1),  new GroupOperation(OperationCategory.MIN, 5), new GroupOperation(OperationCategory.PLUS, 3), new GroupOperation(OperationCategory.MULTIPLY, 2), new GroupOperation(OperationCategory.MIN, 50),  new GroupOperation(OperationCategory.PLUS, 10)};
        Exercise ex1 = new Exercise("RANDOM NAME", "10", null, "Hoeveel is 5 + 5?", new Category("MATH"), Arrays.asList(goArray));
        Exercise ex2 = new Exercise("ANOTHER RANDOM NAME", "2003", "Zoek via wikipedia naar het correcte antwoord.", "In welk jaar is het boek De Davinci Code uitgegeven", new Category("DUTCH"), Arrays.asList(goArray));
        Category math=new Category("MATH");
        Category dutch=new Category("DUTCH");
        Category geography=new Category("GEOGRAPHY");
        
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        //Seeding groupoperations (no cascading persist)
        Arrays.stream(goArray).forEach(e -> em.persist(e));
        
        //Seeding exercises
        em.persist(ex1);
        em.persist(ex2);
        //Seeding Categories
        em.persist(math);
        em.persist(dutch);
        em.persist(geography);
        
        em.getTransaction().commit();
        em.close();
    //    emf.close();
        
    }
}
