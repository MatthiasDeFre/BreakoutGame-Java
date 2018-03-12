


package domain.managers;

import domain.AccessCode;
import domain.Category;
import domain.Exercise;
import domain.Goal;
import domain.GroupOperation;
import domain.PersistMode;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class ExerciseManager extends Manager<Exercise>
{
    private ObservableList<GroupOperation> groupOperationsTemp;
    private ObservableList<Goal> goalsTemp;
    private ObservableList<Category> categories;
    private ObservableList<Category> filterCat;
    protected ExerciseManager()
    {
        super(Exercise.class, new PersistenceController());
      //  setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
      groupOperationsTemp = FXCollections.observableArrayList();
      goalsTemp = FXCollections.observableArrayList();
      
    }

    public ExerciseManager(PersistenceController persistence)
    {
        super(Exercise.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
        groupOperationsTemp = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList(persistence.getAllOfType(Category.class));
        filterCat = FXCollections.observableArrayList(categories);
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
         getFilteredItems().setPredicate(e -> !exercises.contains(e) && filterCat.contains((Category)e.categoryProperty().get()));
         System.out.println("");
     }
     
     public void addCategoryToFilter(Category cat) {
         if(filterCat.size() == categories.size()) 
                 filterCat.removeAll(categories);
         if(cat != null && !filterCat.contains(cat)) {
          
             filterCat.add(cat);
         }
           
     }
    
      public void removeCategoryToFilter(Category cat) {
         if(cat != null && filterCat.contains(cat)) {
               filterCat.remove(cat);
               if(filterCat.isEmpty())
                   filterCat.addAll(categories);
         }
           
     }
    
    
}
