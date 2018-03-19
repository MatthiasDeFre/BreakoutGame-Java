


package domain.managers;

import domain.Classroom;
import domain.Group;
import domain.Student;
import domain.StudentClass;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class GroupManager extends Manager<Group>
{

  
    
    protected GroupManager()
    {
         this(new PersistenceController());
    }
    
    public GroupManager(PersistenceController persistence)
    {
          super(Group.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Group.class)));
           
    }
    
    public static List<Group> generateRandomGroups(StudentClass studentClass, int amount) {
        List<Student> students = new ArrayList<>(studentClass.getStudents()); //GET CLASSROOM STUDENTS TODO
        List<Group> groups = new ArrayList<>();
        int counter = 0;
        int groupSize = students.size() / amount;
        Collections.shuffle(students);
        while (students.size() >= groupSize)
        {            
            List<Student> studentsToBeAdded = new ArrayList<>();
            IntStream.range(0, groupSize).forEach(e -> {
                System.out.println(e);
                System.out.println("List " + students.size());
                studentsToBeAdded.add(students.remove(0));
            });
            groups.add(new Group("Groep " + ++counter, studentsToBeAdded));
        }
        //classroom % amount != 0
        if(Math.ceil((double) students.size() / 2) >= Math.ceil((double)groupSize / 2)) {
            groups.add(new Group("Groep " + ++counter, students));
        } else {
            int counterRemaining = 0;
            while (!students.isEmpty())
            {                
                groups.get(counterRemaining++).addStudent(students.remove(0));
            }
        }
            
            /*  if(students.size() > 0) 
            groups.add(new Group("Groep " + counter, students));*/
        
        return groups;
    }
    
    public static List<Group> generateEmptyGroups(int amount) {
        List<Group> groups = new ArrayList<>();
        IntStream.rangeClosed(1, amount).forEach(e -> {
            groups.add(new Group("Groep " + e));
        });
        return groups;
    }
    
  
}
