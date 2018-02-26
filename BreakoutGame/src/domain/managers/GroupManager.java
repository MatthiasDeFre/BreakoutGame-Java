


package domain.managers;

import domain.Classroom;
import domain.Group;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupManager extends Manager<Group>
{

    protected GroupManager()
    {
          super(Group.class, new PersistenceController());
    }
    
    public GroupManager(PersistenceController persistence)
    {
          super(Group.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Group.class)));
    }
    
}
