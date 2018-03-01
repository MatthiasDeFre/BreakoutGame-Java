/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.GroupOperationManager;
import domain.managers.ExerciseManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class UseCaseExerciseAdminController  {

    private PersistenceController persistenceController;
    private GroupOperationManager groupOperationManager;
    private ExerciseManager exerciseManager;
    

    private Exercise exercise;

    public UseCaseExerciseAdminController() {
        persistenceController = new PersistenceController();
        groupOperationManager = new GroupOperationManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
    }

    public List<String> getListAllExercises() {
        return persistenceController.getAllOfType(Exercise.class).stream().map(e -> e.toString()).collect(Collectors.toList());

    }

    public ObservableList<Exercise> getListAllExercisesE() {
        return exerciseManager.getItems();

    }
    public ObservableList getGroupOperations() {
        return groupOperationManager.getFilteredItems();
    }
    public void setExercise(Exercise exercise) {
        //this.exercise = exercise;
        exerciseManager.setSelected(exercise);
        System.out.println(exerciseManager.getCategories());
       // setChanged();
       // notifyObservers(exercise);
    }
    
  //  public void 
    public ObservableList<GroupOperation> getGroupOperationsTemp() {
        return exerciseManager.getGroupOperationsTemp();
    }
    public ObservableList<Category> getCategories() {
        return exerciseManager.getCategories();
    }
    public void changeFilterGroupOperations(List<GroupOperation> groupOperations) {
        groupOperationManager.changeFilter(groupOperations);
    }
    public void saveExercise(Exercise exercise) {
        exerciseManager.save(exercise);
    }
    public void saveExercise(String name, String answer, String feedback, String assignment, int categoryId) {
        exerciseManager.save(new Exercise(name, answer, feedback, assignment, exerciseManager.getCategory(categoryId), exerciseManager.getGroupOperationsTemp()));
    }
    public void deleteExercise() {
        exerciseManager.delete();
    }
    public void deleteExercise(Exercise exercise) {
     //   exerciseManager.delete(exercise);
    }
    public void setManagerMode(PersistMode persistMode) {
        persistenceController.setPersistMode(persistMode);
    }
    
    public void addObserverExercise(Observer obs) {
        exerciseManager.addObserver(obs);
    }

     public void addObserverGroupOperation(Observer obs) {
        groupOperationManager.addObserver(obs);
    }
     public void removeObserverExercise(Observer obs) {
         exerciseManager.removeObserver(obs);
     }
     public void removeObserverGroupOperation(Observer obs) {
         groupOperationManager.removeObserver(obs);
     }
}
