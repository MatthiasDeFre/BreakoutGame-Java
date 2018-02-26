


package domain;

import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ExerciseManager extends Manager<Exercise>
{
    protected ExerciseManager()
    {
        super(Exercise.class, new PersistenceController());
      //  setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
    }

    public ExerciseManager(PersistenceController persistence)
    {
        super(Exercise.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
    }

    @Override
    public void save(Exercise object)
    {
        ((Exercise) getSelected()).copy(object);
        System.out.println(((Exercise) getSelected()).getId());
        getPersistenceController().persistObject(Exercise.class, getSelected());
    }
    

    
}
