


package domain.managers;

import domain.BoBAction;
import domain.Box;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ActionManager extends Manager<BoBAction>
{

    protected ActionManager()
    {
         super(BoBAction.class, new PersistenceController());
    }
    
    public ActionManager(PersistenceController persitence)
    {
           super(BoBAction.class, persitence);
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(BoBAction.class)));
    }

}
