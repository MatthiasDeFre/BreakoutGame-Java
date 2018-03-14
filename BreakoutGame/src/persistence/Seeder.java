/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.Category;
import domain.Exercise;
import domain.Goal;
import domain.GroupOperation;
import domain.OperationCategory;
import domain.Session;
import domain.Student;
import domain.StudentClass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistence.repositories.BoxRepository;

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
        
           Set<Goal> goals = new HashSet<>(Arrays.asList(new Goal[] {
            new Goal("C50"),
             new Goal("C1"),
              new Goal("E45"),
               new Goal("E74"),
                new Goal("D65"),
                 new Goal("B56")
                  
        }));
        
        
        Exercise ex1 = new Exercise("RANDOM NAME", "10",  "test", "Hoeveel is 5 + 5?", math, Arrays.asList(goArray));
        Exercise ex2 = new Exercise("ANOTHER RANDOM NAME", "2003", "Zoek via wikipedia naar het correcte antwoord.", "In welk jaar is het boek De Davinci Code uitgegeven", dutch, (Arrays.asList(goArray)).subList(0, 3), goals);
        StudentClass studentClass=new StudentClass("2c1");
        Student student=new Student("Jelle","Geers");
        Set<Exercise> exercises = new HashSet<>();
     
        exercises.add(ex2);
        exercises.add(ex1);
        AccessCode a1 = new AccessCode(52);
         AccessCode a2 = new AccessCode(40);
          AccessCode a3 = new AccessCode(32);
           AccessCode a4 = new AccessCode(556);
         AccessCode a5 = new AccessCode(1);
         AccessCode a6 = new AccessCode(23);
         AccessCode a7 = new AccessCode(32131);
         AccessCode a8 = new AccessCode(545);
         AccessCode a9 = new AccessCode(7758);
          List<AccessCode> accesscodes = new ArrayList<>();
          
           accesscodes.add(a1);
           accesscodes.add(a2);
           accesscodes.add(a3);
           accesscodes.add(a4);
           accesscodes.add(a5);
            accesscodes.add(a6);
             accesscodes.add(a7);
              accesscodes.add(a8);
               accesscodes.add(a9);
           
           BoBAction action = new BoBAction("Doe iets");
           BoBAction action2 = new BoBAction("Zoek een kist");
           
           List<BoBAction> acs = new ArrayList<>();
           acs.add(action2);
           acs.add(action);
          
           List<Exercise> test = new ArrayList();
           test.add(ex1);
           test.add(ex2);
        Box box = new Box("Box voor 22/05","BoxMDF1", new HashSet<>(test), accesscodes, acs);
        
        Session session = new Session(box, null, "NIEUWE OMSCHRIJVING", studentClass, "NIEUWE NAAM", LocalDate.now(), true, true);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BreakoutGamePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        //Seeding groupoperations (no cascading persist)
        Arrays.stream(goArray).forEach(e -> em.persist(e));

        //Seeding Categories
        em.persist(math);
        em.persist(dutch);
        em.persist(geography);
        
        //Seeding goals
        goals.forEach(e -> em.persist(e));
        
        //Seeding exercises
        em.persist(ex1);
        em.persist(ex2);
        
        //Seeding actions
        acs.forEach(e -> em.persist(e));
      
        //Seeding accesscodes
        accesscodes.forEach(e -> em.persist(e));
        
        //Seeding box
        em.persist(box);
        
        //Seeding Student & class
        em.persist(studentClass);
        em.persist(student);
        
        em.persist(session);

        
        em.getTransaction().commit();
        em.close();
     
    //    emf.close();
        BoxRepository boxRepository = new BoxRepository();
        System.out.println(boxRepository.contains("BoxMDF1"));
    }
}
