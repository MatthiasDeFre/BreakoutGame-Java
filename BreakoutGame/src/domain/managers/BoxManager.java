


package domain.managers;

import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.Exercise;
import domain.Goal;
import domain.PersistMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import persistence.PersistenceController;


public class BoxManager extends Manager<Box>
{
    
    private ObservableList<AccessCode> accessCodesTemp;
    private ObservableList<BoBAction> actionsTemp;
    private ObservableList<Exercise> exerciseTemp;
    private Map<String, ObservableList> tempLists;
    private ObservableList<Goal> goals;
    
    protected BoxManager()
    {
           super(Box.class, new PersistenceController());
             tempLists = new HashMap<>();
           resetTempCollections();
           goals = FXCollections.observableArrayList();
           /*goals.clear();
           goals = FXCollections.observableSet(exerciseTemp.stream().map(e -> ((Exercise)e).getGoals()).flatMap(List::stream).collect(Collectors.toSet()));*/
         
    }
    
    public BoxManager(PersistenceController persistence)
    {
        super(Box.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Box.class)));
         tempLists = new HashMap<>();
         goals = FXCollections.observableArrayList();
        resetTempCollections();
      
     
    }

  /*  public ObservableList<AccessCode> getAccessCodesTemp()
    {
        return tempLists.get(AccessCode.class.getSimpleName());
    }

   public void setAccessCodesTemp(List<AccessCode> accessCodesTemp)
    {
        //this.accessCodesTemp = accessCodesTemp;
      
        tempLists.get(AccessCode.class.getSimpleName()).setAll(accessCodesTemp);
    }
*/
    public ObservableList<BoBAction> getActionsTemp()
    {
        return tempLists.get(BoBAction.class.getSimpleName());
    }

    public void setActionsTemp(List<BoBAction> actionsTemp)
    {
        tempLists.get(BoBAction.class.getSimpleName()).setAll(actionsTemp);
       // this.actionsTemp = actionsTemp;
    }

    public ObservableList<Exercise> getExerciseTemp()
    {
        return tempLists.get(Exercise.class.getSimpleName());
    }

    public void setExerciseTemp(List<Exercise> exerciseTemp)
    {
      //  this.exerciseTemp = exerciseTemp;
          tempLists.get(Exercise.class.getSimpleName()).setAll(exerciseTemp);
    }
    public ObservableList<Goal> getGoals() {
        return goals;
    }
    private void resetTempCollections() {     
        tempLists.put(Exercise.class.getSimpleName(), FXCollections.observableArrayList());
        tempLists.put(BoBAction.class.getSimpleName(), FXCollections.observableArrayList());
         //tempLists.put(AccessCode.class.getSimpleName(), FXCollections.observableArrayList());
                
   /*     setAccessCodesTemp(FXCollections.observableArrayList());
        setActionsTemp(FXCollections.observableArrayList());
        setExerciseTemp(FXCollections.observableArrayList());*/
    }
    public <T extends IManageable> void addObjectToTemp(List<T> object) {
        String key = object.get(0).getClass().getSimpleName();
        if(key.equals(BoBAction.class.getSimpleName())) {
            
              tempLists.get(key).addAll(tempLists.get(key).size()-1,object);
        }
        else
           tempLists.get(key).addAll(object);
        resetBoxGoalSet();
        
       
    }
    public  <T extends IManageable> void removeObjectFromTemp(List<T> object) {
         String key = object.get(0).getClass().getSimpleName();
        tempLists.get(key).removeAll(object);
        resetBoxGoalSet();
    }
    
    
   /* public void addAccessCodeTemp(AccessCode accessCode)
    {
        accessCodesTemp.add(accessCode); 
   }

    public void removeAccessCodeTemp(AccessCode accessCode)
    {
        accessCodesTemp.remove(accessCode);
    }*/

    @Override
    public void save(Box object)
    {   
        getPersistenceController().setPersistMode(getManagerMode());
        ((Box) getSelected() ).copy(object);
        super.save(object);
    }

    
    public void changeBoxNameFilter(String name) {
        getFilteredItems().setPredicate(e -> e.getName().toLowerCase().contains(name.toLowerCase().trim()));
    }
    
    @Override
    public void setSelected(Box item)
    {
      //  setAccessCodesTemp(item.getAccessCodes());
        setActionsTemp(item.getActions());
        setExerciseTemp(item.getExercises().stream().collect(Collectors.toList()));
        resetBoxGoalSet();
        super.setSelected(item); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void resetBoxGoalSet() {
         goals.clear();
        ObservableList<Exercise> x = tempLists.get(Exercise.class.getSimpleName());
        goals.addAll(x.stream().map(e -> ((Exercise)e).getGoals()).flatMap(Set::stream).collect(Collectors.toSet()));
    }
    
    
    
    

}
