


package domain.managers;

import domain.Action;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ActionManager extends Manager<Action>
{

    protected ActionManager()
    {
        super();
    }
    
    public ActionManager(PersistenceController persitence)
    {
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(Action.class)));
    }

}
