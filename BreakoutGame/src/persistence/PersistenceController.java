/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import persistence.repositories.IGenericRepository;
import persistence.repositories.BoxRepository;
import persistence.repositories.ExerciseRepository;
import persistence.repositories.StudentRepository;
import persistence.repositories.GroupRepository;
import persistence.repositories.ClassroomRepository;
import persistence.repositories.ActionRepository;
import persistence.repositories.SessionRepository;
import persistence.repositories.GroupOperationRepository;
import domain.*;
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
        repositories.put(Action.class.getSimpleName(), new ActionRepository());
        repositories.put(Box.class.getSimpleName(),new BoxRepository());
        //category?
        repositories.put(Classroom.class.getSimpleName(), new ClassroomRepository());
        repositories.put(Exercise.class.getSimpleName(),  new ExerciseRepository());
        repositories.put(GroupOperation.class.getSimpleName(), new GroupOperationRepository());
        repositories.put(Group.class.getSimpleName(),new GroupRepository());
        repositories.put(Session.class.getSimpleName(),new SessionRepository());
        repositories.put(Student.class.getSimpleName(),new StudentRepository());
        //put other repos
        
        
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
