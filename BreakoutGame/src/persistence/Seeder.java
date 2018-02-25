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
        
        GroupOperation[] goArray = { new GroupOperation(OperationCategory.MULTIPLY, "1"),  new GroupOperation(OperationCategory.MIN, "1"), new GroupOperation(OperationCategory.PLUS, "1"), new GroupOperation(OperationCategory.MULTIPLY, "2"), new GroupOperation(OperationCategory.MIN, "50"),  new GroupOperation(OperationCategory.PLUS, "10"), new GroupOperation(OperationCategory.SWAPCHAR, "a&b")};
     
        Category math=new Category("MATH");
        Category dutch=new Category("DUTCH");
        Category geography=new Category("GEOGRAPHY");
        
        Exercise ex1 = new Exercise("RANDOM NAME", "10", null, "Hoeveel is 5 + 5?", math, Arrays.asList(goArray));
        Exercise ex2 = new Exercise("ANOTHER RANDOM NAME", "2003", "Zoek via wikipedia naar het correcte antwoord.", "In welk jaar is het boek De Davinci Code uitgegeven", dutch, Arrays.asList(goArray));
        
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        //Seeding groupoperations (no cascading persist)
        Arrays.stream(goArray).forEach(e -> em.persist(e));

        //Seeding Categories
        em.persist(math);
        em.persist(dutch);
        em.persist(geography);
        
        //Seeding exercises
        em.persist(ex1);
        em.persist(ex2);

        
        em.getTransaction().commit();
        em.close();
    //    emf.close();
        
    }
}
