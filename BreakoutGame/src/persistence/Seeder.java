/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

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
        
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Arrays.stream(goArray).forEach(e -> em.persist(e));
        em.getTransaction().commit();
        emf.close();
    }
}
