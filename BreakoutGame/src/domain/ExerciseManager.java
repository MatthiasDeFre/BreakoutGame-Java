


package domain;

import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ExerciseManager<Exercise> extends Manager<IManageable>
{
    protected ExerciseManager()
    {
        super();
    }

    public ExerciseManager(PersistenceController persistence)
    {
        //pass this classname, Exercise is prohibited, member of type param section
        setItems(FXCollections.observableList(persistence.getAllOfType(this.getClass())));
    }

    
}
