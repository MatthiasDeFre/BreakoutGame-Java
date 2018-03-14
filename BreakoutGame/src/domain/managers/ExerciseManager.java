


package domain.managers;

import domain.AccessCode;
import domain.Category;
import domain.Exercise;
import domain.Goal;
import domain.GroupOperation;
import domain.PersistMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class ExerciseManager extends Manager<Exercise>
{
    private ObservableList<GroupOperation> groupOperationsTemp;
    private ObservableList<Goal> goalsTemp;
    private ObservableList<Category> categories;
    private ObservableList<Category> filterCat;
    private ObservableList<Goal> goals;
    private List<String> goalFilter;
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
        goals = FXCollections.observableArrayList(persistence.getAllOfType(Goal.class).stream().sorted().collect(Collectors.toList()));
        filterCat = FXCollections.observableArrayList(categories);
        goalFilter = new ArrayList<>();
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
    
    public ObservableList<Goal> getGoals() {
        return goals;
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
         getFilteredItems().setPredicate(e -> !exercises.contains(e) && filterCat.contains((Category)e.categoryProperty().get()) && 
                 (!Collections.disjoint(e.getGoals().stream().map(e2 -> e2.getCode().toLowerCase()).collect(Collectors.toList()), goalFilter) || goalFilter.isEmpty()));
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
     
      public void changeGoalFilter(List<String> goals) {
          
          this.goalFilter = goals;
      }
    
}
