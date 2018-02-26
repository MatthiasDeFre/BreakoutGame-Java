


package domain;

import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupOperationManager extends Manager<GroupOperation>
{

    protected GroupOperationManager()
    {
         super(GroupOperation.class, new PersistenceController());
      //  setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
    }
    

    public GroupOperationManager(PersistenceController persistence)
    {
         super(GroupOperation.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(GroupOperation.class)));
    }
    
    @Override
    public void save(GroupOperation object)
    {
        ((GroupOperation) getSelected()).copy(object);
    }
    
}
