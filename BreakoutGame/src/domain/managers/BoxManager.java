


package domain.managers;

import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.Exercise;
import domain.PersistMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class BoxManager extends Manager<Box>
{
    
    private ObservableList<AccessCode> accessCodesTemp;
    private ObservableList<BoBAction> actionsTemp;
    private ObservableList<Exercise> exerciseTemp;
    private Map<String, ObservableList> tempLists;
    
    protected BoxManager()
    {
           super(Box.class, new PersistenceController());
             tempLists = new HashMap<>();
           resetTempCollections();
         
    }
    
    public BoxManager(PersistenceController persistence)
    {
        super(Box.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Box.class)));
         tempLists = new HashMap<>();
        resetTempCollections();
      
     
    }

    public ObservableList<AccessCode> getAccessCodesTemp()
    {
        return tempLists.get(AccessCode.class.getSimpleName());
    }

    public void setAccessCodesTemp(List<AccessCode> accessCodesTemp)
    {
        //this.accessCodesTemp = accessCodesTemp;
        tempLists.get(AccessCode.class.getSimpleName()).setAll(accessCodesTemp);
    }

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
    
    private void resetTempCollections() {     
        tempLists.put(Exercise.class.getSimpleName(), FXCollections.observableArrayList());
        tempLists.put(BoBAction.class.getSimpleName(), FXCollections.observableArrayList());
         tempLists.put(AccessCode.class.getSimpleName(), FXCollections.observableArrayList());
                
   /*     setAccessCodesTemp(FXCollections.observableArrayList());
        setActionsTemp(FXCollections.observableArrayList());
        setExerciseTemp(FXCollections.observableArrayList());*/
    }
    public void addObjectToTemp(Object object) {
        tempLists.get(object.getClass().getSimpleName()).add(object);
    }
    public void removeObjectFromTemp(Object object) {
        tempLists.get(object.getClass().getSimpleName()).remove(object);
    }
    
    
    public void addAccessCodeTemp(AccessCode accessCode)
    {
        accessCodesTemp.add(accessCode); 
   }

    public void removeAccessCodeTemp(AccessCode accessCode)
    {
        accessCodesTemp.remove(accessCode);
    }

    @Override
    public void save(Box object)
    {
        ((Box) getSelected() ).copy(object);
        super.save(object);
    }

    
    
    @Override
    public void setSelected(Box item)
    {
        setAccessCodesTemp(item.getAccessCodes());
        setActionsTemp(item.getActions());
        setExerciseTemp(item.getExercises().stream().collect(Collectors.toList()));
        super.setSelected(item); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
