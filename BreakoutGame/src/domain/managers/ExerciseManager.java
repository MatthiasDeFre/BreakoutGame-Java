


package domain.managers;

import domain.AccessCode;
import domain.Category;
import domain.Exercise;
import domain.GroupOperation;
import domain.PersistMode;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class ExerciseManager extends Manager<Exercise>
{
    private ObservableList<GroupOperation> groupOperationsTemp;
    private ObservableList<Category> categories;
    protected ExerciseManager()
    {
        super(Exercise.class, new PersistenceController());
      //  setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
      groupOperationsTemp = FXCollections.observableArrayList();
      
    }

    public ExerciseManager(PersistenceController persistence)
    {
        super(Exercise.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
        groupOperationsTemp = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList(persistence.getAllOfType(Category.class));
    }

    public void addGroupOperationTemp(GroupOperation groupOperation) {
        groupOperationsTemp.add(groupOperation);
    }
    public void removeGroupOperationTemp(GroupOperation groupOperation) {
        groupOperationsTemp.remove(groupOperation);
    }
    public ObservableList<GroupOperation> getGroupOperationsTemp() {
        return groupOperationsTemp;
    }
    
    public ObservableList<Category> getCategories() {
        return categories;
    }
    public Category getCategory(int id) {
        return categories.get(id);
    }
    @Override
    public void save(Exercise object)
    {
         getPersistenceController().setPersistMode(getManagerMode());
        ((Exercise) getSelected()).copy(object);
        System.out.println(((Exercise) getSelected()).getId());
        super.save(object);
    }

    @Override
    public void setSelected(Exercise item)
    {
        groupOperationsTemp = FXCollections.observableArrayList(item.getGroupOperations());
        super.setSelected(item);
      
    }
     public void changeFilter(List<Exercise> exercises) {
         getFilteredItems().setPredicate(e -> !exercises.contains(e));
         System.out.println("");
     }
    

    
}
