/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.AccessCodeManager;
import domain.managers.ActionManager;
import domain.managers.BoxManager;
import domain.managers.ExerciseManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class BoxController {
    
    private PersistenceController persistenceController;
    private BoxManager boxManager;
    private ExerciseManager exerciseManager;
    private ActionManager actionManager;
    private AccessCodeManager accessCodeManager;
    
    private Map<String, ManagerFilter> filters;

    public BoxController(PersistenceController pc)
    {
        persistenceController = pc;
        boxManager= new BoxManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
        actionManager = new ActionManager(persistenceController);
        accessCodeManager = new AccessCodeManager(persistenceController);
        
        filters = new HashMap<>();
        
        filters.put(Exercise.class.getSimpleName(), () -> exerciseManager.changeFilter(boxManager.getExerciseTemp()));
        filters.put(BoBAction.class.getSimpleName(), () ->  actionManager.changeFilter(boxManager.getActionsTemp()));
        filters.put(AccessCode.class.getSimpleName(), () -> accessCodeManager.changeFilter(boxManager.getAccessCodesTemp()));
        
    }
    
    
    public FilteredList getBoxes() {
        return boxManager.getFilteredItems();
    }
    
    public FilteredList getExercises() {
        return exerciseManager.getFilteredItems();
    }
    
    public FilteredList getActions() {
        return actionManager.getFilteredItems();
    }
    
    public FilteredList getAccessCodes() {
        return accessCodeManager.getFilteredItems();
    }
    
    public void setSelectedBox(Box box) {
        boxManager.setSelected(box);
        applyFilters();
    }
    
    public void addObserverBox(Observer obs) {
        boxManager.addObserver(obs);
    }
    
    public ObservableList<AccessCode> getTempListAccessCodes() {
        return boxManager.getAccessCodesTemp();
    }
     public ObservableList<Exercise> getTempListExercises() {
        return boxManager.getExerciseTemp();
    }
      public ObservableList<BoBAction> getTempListBoBActions() {
        return  boxManager.getActionsTemp();
    }
      
    public void applyFilters() {
     //   ManagerFilter test = () -> exerciseManager.changeFilter(boxManager.getExerciseTemp());
       // exerciseManager.changeFilter(boxManager.getExerciseTemp());
    //    actionManager.changeFilter(boxManager.getActionsTemp());
      //  accessCodeManager.changeFilter(boxManager.getAccessCodesTemp());
        filters.entrySet().forEach(e -> e.getValue().applyFilter());
    }
    
    public void saveBox(String name, String description) {
        Box box = new Box(description, name, new HashSet<>(boxManager.getExerciseTemp()), boxManager.getAccessCodesTemp(), boxManager.getActionsTemp());
        boxManager.save(box);
    }
      public void setManagerMode(PersistMode persistMode) {
        persistenceController.setPersistMode(persistMode);
    }
    
      public void addToTempList(Object obj) {
          boxManager.addObjectToTemp(obj);
          filters.get(obj.getClass().getSimpleName()).applyFilter();
      }
      
      public void removeFromTempList(Object obj) {
          boxManager.removeObjectFromTemp(obj);
         filters.get(obj.getClass().getSimpleName()).applyFilter();
      }
}
