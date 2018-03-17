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
public class ExerciseDomainController  {

    private PersistenceController persistenceController;
    private GroupOperationManager groupOperationManager;
    private ExerciseManager exerciseManager;
    

    private Exercise exercise;

    public ExerciseDomainController() {
        persistenceController = new PersistenceController();
        groupOperationManager = new GroupOperationManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
    }

   public ObservableList<Exercise> getListAllExercisesE() {
        return exerciseManager.getItems();
 }
    
    public List<Student> getListAllStudents()
    {
        return persistenceController.getAllOfType(Student.class);
    }
    public ObservableList getGroupOperations() {
        return groupOperationManager.getFilteredItems();
    }
    
    public ObservableList getGoals() {
        return exerciseManager.getGoals();
    }
    
    public void setExercise(Exercise exercise) {
        //this.exercise = exercise;
        exerciseManager.setSelected(exercise);
        System.out.println(exerciseManager.getCategories());
       // setChanged();
       // notifyObservers(exercise);
    }
    
    public void setGroupOperation(GroupOperation groupOperation) {
        groupOperationManager.setSelected(groupOperation);
    }
        
  //  public void 
    public ObservableList<GroupOperation> getGroupOperationsTemp() {
        return exerciseManager.getGroupOperationsTemp();
    }
    
    public ObservableList<Goal> getGoalsTemp() {
        return exerciseManager.getGoalsTemp();
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
    
    public void saveGroupOperation(OperationCategory cat, List<String> valueString) {
        groupOperationManager.save(new GroupOperation(cat, valueString.stream().collect(Collectors.joining("&"))));
    }
    public void deleteExercise() {
        exerciseManager.delete();
    }
    
    public void deleteGroupOperation() {
        groupOperationManager.delete();
    }
    
    public void deleteExercise(Exercise exercise) {
      //  exerciseManager.delete(exercise);
    }
    public void setManagerMode(PersistMode persistMode) {
        exerciseManager.setManagerMode(persistMode);
    }
    public void setManagerModeGroupOp(PersistMode persistMode) {
        groupOperationManager.setManagerMode(persistMode);
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
     
     public void addToGroupTemp(GroupOperation temp) {
         exerciseManager.addGroupOperationTemp(temp);
         groupOperationManager.changeFilter(exerciseManager.getGroupOperationsTemp());
     }
     
     public void removeToGroupTemp(GroupOperation temp) {
         exerciseManager.removeGroupOperationTemp(temp);
         groupOperationManager.changeFilter(exerciseManager.getGroupOperationsTemp());
     }
     
     public void addToGoalTemp(Goal temp) {
         exerciseManager.addGoalTemp(temp);
    
     }
     
     public void removeToGoalTemp(Goal temp) {
         exerciseManager.removeGoalTemp(temp);
      
     }
}
