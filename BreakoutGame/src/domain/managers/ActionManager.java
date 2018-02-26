


package domain.managers;

import domain.Action;
import domain.Box;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ActionManager extends Manager<Action>
{

    protected ActionManager()
    {
         super(Action.class, new PersistenceController());
    }
    
    public ActionManager(PersistenceController persitence)
    {
           super(Action.class, persitence);
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(Action.class)));
    }

}
