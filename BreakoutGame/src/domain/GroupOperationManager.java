


package domain;

import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupOperationManager<GroupOperation> extends Manager<IManageable>
{

    protected GroupOperationManager()
    {
        super();
    }
    

    public GroupOperationManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableList(persistence.getAllOfType(this.getClass())));
    }
    
}
