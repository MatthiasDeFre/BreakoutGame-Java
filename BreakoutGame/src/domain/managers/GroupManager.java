


package domain.managers;

import domain.Group;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupManager extends Manager<Group>
{

    protected GroupManager()
    {
        super();
    }
    
    public GroupManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Group.class)));
    }
    
}
