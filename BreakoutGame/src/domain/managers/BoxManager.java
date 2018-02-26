


package domain.managers;

import domain.Box;
import domain.Exercise;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class BoxManager extends Manager<Box>
{

    protected BoxManager()
    {
           super(Box.class, new PersistenceController());
    }
    
    public BoxManager(PersistenceController persistence)
    {
        super(Box.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Box.class)));
     
    }

    
}
