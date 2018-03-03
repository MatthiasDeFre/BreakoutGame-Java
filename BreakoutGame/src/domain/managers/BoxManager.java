


package domain.managers;

import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.Exercise;
import domain.PersistMode;
import java.util.HashMap;
import java.util.Map;
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
        return accessCodesTemp;
    }

    public void setAccessCodesTemp(ObservableList<AccessCode> accessCodesTemp)
    {
        //this.accessCodesTemp = accessCodesTemp;
        tempLists.put(AccessCode.class.getSimpleName(), accessCodesTemp);
    }

    public ObservableList<BoBAction> getActionsTemp()
    {
        return actionsTemp;
    }

    public void setActionsTemp(ObservableList<BoBAction> actionsTemp)
    {
        tempLists.put(BoBAction.class.getSimpleName(), actionsTemp);
       // this.actionsTemp = actionsTemp;
    }

    public ObservableList<Exercise> getExerciseTemp()
    {
        return exerciseTemp;
    }

    public void setExerciseTemp(ObservableList<Exercise> exerciseTemp)
    {
      //  this.exerciseTemp = exerciseTemp;
          tempLists.put(Exercise.class.getSimpleName(), exerciseTemp);
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
        tempLists.get(object.getClass().getDeclaringClass().getSimpleName()).add(object);
    }
    public void removeObjectFromTemp(Object object) {
        tempLists.get(object.getClass().getDeclaringClass().getSimpleName()).remove(object);
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
        setAccessCodesTemp(FXCollections.observableArrayList(item.getAccessCodes()));
        setActionsTemp(FXCollections.observableArrayList(item.getActions()));
        setExerciseTemp(FXCollections.observableArrayList(item.getExercises()));
        super.setSelected(item); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
