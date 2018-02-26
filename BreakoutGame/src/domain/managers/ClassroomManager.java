


package domain.managers;

import domain.Classroom;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ClassroomManager extends Manager<Classroom>
{

    protected ClassroomManager()
    {
        super();
    }
    
    public ClassroomManager(PersistenceController persitence)
    {
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(Classroom.class)));
    }
    
}
