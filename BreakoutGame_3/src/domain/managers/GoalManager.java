/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.Goal;
import domain.Group;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class GoalManager extends Manager<Goal>{
    
   
    protected GoalManager()
    {
          super(Goal.class, new PersistenceController());
    }
    
    public GoalManager(PersistenceController persistence)
    {
        super(Goal.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Goal.class)));
    }
    
    public void changeFilter(List<Goal> goals) {
         getFilteredItems().setPredicate(e -> !goals.contains(e));
    }
}
