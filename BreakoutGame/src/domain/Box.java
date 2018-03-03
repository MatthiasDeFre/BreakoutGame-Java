/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author geers
 */
@NamedQueries(@NamedQuery(name = "BoxExists", query = "SELECT b FROM Box b WHERE b.name = :name"))
@Entity
public class Box implements Serializable, IManageable {

   private long id;
   private SimpleStringProperty description = new SimpleStringProperty();
   private SimpleStringProperty name = new SimpleStringProperty();
   
   private Set<Exercise> exercises;
   
   private List<AccessCode> accessCodes;
   
   private List<BoBAction> actions;
   
   public Box() {
       //Treasurechest
       actions = new ArrayList<>();
       actions.add(new BoBAction());
   }

    public Box(String description, String name, Set<Exercise> exercises, List<AccessCode> accessCodes, List<BoBAction> actions)
    {
        setDescription(description);
        setName(name);
        setExercises(new HashSet<>(exercises));
        setAccessCodes(new ArrayList<>(accessCodes));
        setActions(new ArrayList<>(actions));
    }
    
    public void copy(Box box) {
        setDescription(box.getDescription());
        setName(box.getName());
        setExercises(new HashSet<>(box.getExercises()));
        setAccessCodes(new ArrayList<>(box.getAccessCodes()));
        setActions(new ArrayList<>(box.getActions()));
    }
   
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

     @Column(name = "description")
    public String getDescription()
    {
        return description.get();
    }

    public void setDescription(String description)
    {
        this.description.set(description);
    }
    
   @Column(name = "name")
    public String getName()
    {
        return name.get();
    }
    
   
    public void setName(String name)
    {
        this.name.set(name);
    }

    @ManyToMany
    public Set<Exercise> getExercises()
    {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises)
    {
        this.exercises = exercises;
    }

    @ManyToMany
    public List<AccessCode> getAccessCodes()
    {
        return accessCodes;
    }

    public void setAccessCodes(List<AccessCode> accessCodes)
    {
        this.accessCodes = accessCodes;
    }

    @ManyToMany
    public List<BoBAction> getActions()
    {
        return actions;
    }

    public void setActions(List<BoBAction> actions)
    {
        this.actions = actions;
    }
    
    public void addAction(BoBAction action) {
        actions.add(actions.size() -2, action);
       
    }
    public void removeAction(BoBAction action) {
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
   
    public boolean isValidBox() {
        return getActions().size() -1 == getExercises().size();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
     public StringProperty descriptionProperty() {
        return description;
    }
    
}
