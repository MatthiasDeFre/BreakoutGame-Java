/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author geers
 */
@Entity
public class Box implements Serializable, IManageable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String description;
   private String name;
   
   @ManyToMany
   private ObservableSet<Exercise> exercises;
   
   @ManyToMany
   private ObservableList<AccessCode> accessCodes;
   
   @ManyToMany
   private ObservableList<Action> actions;
   protected Box() {
       //Treasurechest
       actions.add(new Action());
   }
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ObservableSet<Exercise> getExercises()
    {
        return exercises;
    }

    public void setExercises(ObservableSet<Exercise> exercises)
    {
        this.exercises = exercises;
    }

    public ObservableList<AccessCode> getAccessCodes()
    {
        return accessCodes;
    }

    public void setAccessCodes(ObservableList<AccessCode> accessCodes)
    {
        this.accessCodes = accessCodes;
    }

    public ObservableList<Action> getActions()
    {
        return actions;
    }

    public void setActions(ObservableList<Action> actions)
    {
        this.actions = actions;
    }
    
    public void addAction(Action action) {
        actions.add(actions.size() -2, action);
       
    }
    public void removeAction(Action action) {
        //Check if equal to treasure (cant remove treasure = exception)
        actions.remove(action);
    }
    public void addAccessCode(AccessCode accessCode) {
        accessCodes.add(accessCode);
    }
    public void removeAccessCode(AccessCode accessCode) {
        accessCodes.remove(accessCode);
    }
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }
   
}
