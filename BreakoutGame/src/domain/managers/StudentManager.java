


package domain.managers;

import domain.Student;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class StudentManager extends Manager<Student>
{

    protected StudentManager()
    {
        super();
    }
    
    public StudentManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Student.class)));
    }
    
}
