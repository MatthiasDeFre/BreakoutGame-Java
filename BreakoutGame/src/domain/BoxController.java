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
import java.util.Observer;
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

    public BoxController(PersistenceController pc)
    {
        persistenceController = pc;
        boxManager= new BoxManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
        actionManager = new ActionManager(persistenceController);
        accessCodeManager = new AccessCodeManager(persistenceController);
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
        exerciseManager.changeFilter(boxManager.getExerciseTemp());
        actionManager.changeFilter(boxManager.getActionsTemp());
        accessCodeManager.changeFilter(boxManager.getAccessCodesTemp());
    }
}
