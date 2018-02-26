


package domain.managers;

import domain.Box;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class BoxManager extends Manager<Box>
{

    protected BoxManager()
    {
        super();
    }
    
    public BoxManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableList(persistence.getAllOfType(Box.class)));
    }

    
}
