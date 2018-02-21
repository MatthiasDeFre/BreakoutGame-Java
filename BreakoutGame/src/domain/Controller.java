/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class Controller extends Observable {

    private PersistenceController persistenceController;

//    private Exercise exercise;

    public Controller() {
        persistenceController = new PersistenceController();
    }

    public List<String> getListAllExercises() {
        return persistenceController.getListAllExercises().stream().map(e -> e.toString()).collect(Collectors.toList());

    }

    public List<Exercise> getListAllExercisesE() {
        return persistenceController.getListAllExercises();

    }

//    public void setExercise(Exercise exercise) {
//        this.exercise = exercise;
//        setChanged();
//        notifyObservers(exercise);
//    }

}
