


package domain;

import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupOperationManager extends Manager<GroupOperation>
{

    protected GroupOperationManager()
    {
        super();
    }
    

    public GroupOperationManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableList(persistence.getAllOfType(GroupOperation.class)));
    }
    
}
