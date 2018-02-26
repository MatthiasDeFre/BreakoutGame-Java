


package domain.managers;

import domain.GroupOperation;
import domain.OperationCategory;
import domain.managers.Manager;
import java.util.List;
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
    
     public void changeFilter(List<OperationCategory> categories) {
         getFilteredItems().setPredicate(e -> categories.contains(e.getCategory()));
     }
    
}
