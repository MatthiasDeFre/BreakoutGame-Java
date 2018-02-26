


package domain.managers;

import domain.Group;
import domain.Student;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class StudentManager extends Manager<Student>
{

    protected StudentManager()
    {
         super(Student.class, new PersistenceController());
    }
    
    public StudentManager(PersistenceController persistence)
    {
          super(Student.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Student.class)));
    }
    
}
