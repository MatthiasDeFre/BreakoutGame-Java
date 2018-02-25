/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Exercise;
import domain.ExerciseManager;
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
        repositories.put(ExerciseManager.class.getSimpleName(),  new ExerciseRepository());
        
    }

    /**
     * @param type the Manager class for the requested objects
     * @return a list of objects of type T
     */
    public <T> List<T> getAllOfType(Class type)
    {
        IGenericRepository repository = repositories.get(type.getSimpleName());
        return repository.getAll();
    }
    
}
