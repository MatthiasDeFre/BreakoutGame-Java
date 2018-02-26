


package domain.managers;

import domain.Exercise;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ExerciseManager extends Manager<Exercise>
{
    protected ExerciseManager()
    {
        super();
    }

    public ExerciseManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
    }

    
}
