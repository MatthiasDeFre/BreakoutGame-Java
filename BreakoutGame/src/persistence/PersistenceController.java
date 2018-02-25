/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Exercise;
import domain.ExerciseManager;
import domain.GroupOperation;
import domain.GroupOperationManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author geers
 */
public class PersistenceController {
    private Map<String,IGenericRepository> repositories;

    public PersistenceController()
    {
        repositories = new HashMap<>();
        repositories.put(Exercise.class.getSimpleName(),  new ExerciseRepository());
        repositories.put(GroupOperation.class.getSimpleName(), new GroupOperationRepository());
        
    }

    /**
     * @param type the class for the requested objects
     * @return a list of objects of type T
     */
    public <T> List<T> getAllOfType(Class<T> type)
    {
        IGenericRepository repository = repositories.get(type.getSimpleName());
        return repository.getAll();
    }
    
}
