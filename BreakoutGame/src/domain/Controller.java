/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class Controller {
    
    private PersistenceController persistenceController;

    public Controller() {
        persistenceController=new PersistenceController();
    }
    
    
    public List<Exercise> getListAllExercises()
    {
        return persistenceController.getListAllExercises();
        
    }
    
}
