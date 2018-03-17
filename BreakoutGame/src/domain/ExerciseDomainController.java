/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.GroupOperationManager;
import domain.managers.ExerciseManager;
import domain.managers.GoalManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
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
    private GoalManager goalManager;

    private Exercise exercise;

    public ExerciseDomainController() {
        persistenceController = new PersistenceController();
        groupOperationManager = new GroupOperationManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
        goalManager = new GoalManager(persistenceController);
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
        return goalManager.getFilteredItems();
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
    public void changeFilterGoal(List<Goal> goals) {
        goalManager.changeFilter(goals);
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
     
     public void addToGroupTemp(List<GroupOperation> temp) {
         exerciseManager.addGroupOperationTemp(temp);
         groupOperationManager.changeFilter(exerciseManager.getGroupOperationsTemp());
     }
     
     public void removeToGroupTemp(List<GroupOperation> temp) {
         exerciseManager.removeGroupOperationTemp(temp);
         groupOperationManager.changeFilter(exerciseManager.getGroupOperationsTemp());
     }
     
     public void addToGoalTemp(List<Goal> temp) {
         exerciseManager.addGoalTemp(temp);
         goalManager.changeFilter(exerciseManager.getGoalsTemp());
    
     }
     
     public void removeToGoalTemp(List<Goal> temp) {
         exerciseManager.removeGoalTemp(temp);
         goalManager.changeFilter(exerciseManager.getGoalsTemp());
      
     }
}
