/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.GroupOperationManager;
import domain.managers.ExerciseManager;
import domain.managers.GoalManager;
import domain.managers.IManageable;
import domain.managers.Manager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ExerciseDomainController {

    private PersistenceController persistenceController;
    private GroupOperationManager groupOperationManager;
    private ExerciseManager exerciseManager;
    private GoalManager goalManager;

    private Map<String, Manager> managers;
    private Map<String, ManagerFilter> filters;

    private Exercise exercise;

    public ExerciseDomainController()
    {
        persistenceController = new PersistenceController();
        groupOperationManager = new GroupOperationManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
        goalManager = new GoalManager(persistenceController);

        managers = new HashMap<>();

        managers.put(Exercise.class.getSimpleName(), exerciseManager);
        managers.put(GroupOperation.class.getSimpleName(), groupOperationManager);
        managers.put(Goal.class.getSimpleName(), goalManager);

        filters = new HashMap<>();

        filters.put(GroupOperation.class.getSimpleName(), () -> groupOperationManager.changeFilter(exerciseManager.getGroupOperationsTemp()));
        filters.put(Goal.class.getSimpleName(), () -> goalManager.changeFilter(exerciseManager.getGoalsTemp()));
    }

    public FilteredList getFilteredItems(Class<? extends IManageable> className)
    {
        return managers.get(className.getSimpleName()).getFilteredItems();
    }

    public void setSelectedItem(IManageable item)
    {
        managers.get(item.getClass().getSimpleName()).setSelected(item);
    }

    //  public void 
    public ObservableList<GroupOperation> getGroupOperationsTemp()
    {
        return exerciseManager.getGroupOperationsTemp();
    }

    public ObservableList<Goal> getGoalsTemp()
    {
        return exerciseManager.getGoalsTemp();
    }

    public ObservableList<Category> getCategories()
    {
        return exerciseManager.getCategories();
    }

    public void changeFilterGroupOperations(List<GroupOperation> groupOperations)
    {
        groupOperationManager.changeFilter(groupOperations);
    }

    public void changeFilterGoal(List<Goal> goals)
    {
        goalManager.changeFilter(goals);
    }

    public void saveExercise(Exercise exercise)
    {
        exerciseManager.save(exercise);
    }

    public void saveExercise(String name, String answer, String feedback, String assignment, int categoryId, int time)
    {
        exerciseManager.save(new Exercise(name, answer, feedback, assignment, exerciseManager.getCategory(categoryId), exerciseManager.getGroupOperationsTemp(), exerciseManager.getGoalsTemp(), time));
    }

    public void saveGroupOperation(OperationCategory cat, List<String> valueString)
    {
        groupOperationManager.save(new GroupOperation(cat, valueString.stream().collect(Collectors.joining("&"))));
    }

    public void deleteExercise()
    {
        exerciseManager.delete();
    }

    public void deleteGroupOperation()
    {
        groupOperationManager.delete();
    }

    public void deleteExercise(Exercise exercise)
    {
        //  exerciseManager.delete(exercise);
    }

    public void setManagerMode(Class<? extends IManageable> className, PersistMode persistMode) {
          System.out.println(className.getSimpleName());
       managers.get(className.getSimpleName()).setManagerMode(persistMode);
    }

    public void setManagerModeGroupOp(PersistMode persistMode)
    {
        groupOperationManager.setManagerMode(persistMode);
    }

    public void addObserverExercise(Observer obs)
    {
        exerciseManager.addObserver(obs);
    }

    public void addObserverGroupOperation(Observer obs)
    {
        groupOperationManager.addObserver(obs);
    }

    public void addObserverGoal(Observer goal)
    {
        goalManager.addObserver(goal);
    }

    public void removeObserverExercise(Observer obs)
    {
        exerciseManager.removeObserver(obs);
    }

    public void removeObserverGroupOperation(Observer obs)
    {
        groupOperationManager.removeObserver(obs);
    }

    public <T extends IManageable> void addToTempList(List<T> obj)
    {
        if (obj.size() > 0)
        {
            exerciseManager.addObjectToTemp(obj);
            ManagerFilter filter = filters.get(obj.get(0).getClass().getSimpleName());
            if (filter != null)
            {
                filter.applyFilter();
            }
        }
    }

    public <T extends IManageable> void removeFromTempList(List<T> obj)
    {
        //Need to retrieve an object because of type erasure
        if (obj.size() > 0)
        {
            String test = obj.get(0).getClass().getSimpleName();
            exerciseManager.removeObjectFromTemp(obj);
            ManagerFilter filter = filters.get(obj.get(0).getClass().getSimpleName());
            if (filter != null)
            {
                filter.applyFilter();
            }
        }
    }

}
