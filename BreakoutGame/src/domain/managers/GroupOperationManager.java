


package domain.managers;

import domain.Group;
import domain.GroupOperation;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupOperationManager extends Manager<GroupOperation>
{

    protected GroupOperationManager()
    {
        
          super(GroupOperation.class, new PersistenceController());
    }
    

    public GroupOperationManager(PersistenceController persistence)
    {
          super(GroupOperation.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(GroupOperation.class)));
    }
    
}
