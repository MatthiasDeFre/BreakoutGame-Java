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
        Exercise ex1 = new Exercise("10", null, "Hoeveel is 5 + 5?", Category.MATH, Arrays.asList(goArray));
        Exercise ex2 = new Exercise("2003", "Zoek via wikipedia naar het correcte antwoord.", "In welk jaar is het boek De Davinci Code uitgegeven", Category.DUTCH, Arrays.asList(goArray));
        
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        //Seeding groupoperations (no cascading persist)
        Arrays.stream(goArray).forEach(e -> em.persist(e));
        
        //Seeding exercises
        em.persist(ex1);
        em.persist(ex2);
        
        em.getTransaction().commit();
        em.close();
    //    emf.close();
        
    }
}
