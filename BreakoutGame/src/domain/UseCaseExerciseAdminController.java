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
import java.util.stream.Collectors;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class UseCaseExerciseAdminController extends Observable {

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

    public List<Exercise> getListAllExercisesE() {
        return persistenceController.getAllOfType(Exercise.class);

    }

    public void setExercise(Exercise exercise) {
        //this.exercise = exercise;
        exerciseManager.setSelected(exercise);
        setChanged();
        notifyObservers(exercise);
    }
    
    public void createExercise(Exercise exercise) {
        exerciseManager.save(exercise);
    }
    public void deleteExercise(Exercise exercise) {
        exerciseManager.delete(exercise);
    }
    public void setManagerMode(PersistMode persistMode) {
        persistenceController.setPersistMode(persistMode);
    }

}
