/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Group;
import domain.Exercise;
import domain.Category;
import domain.Student;
import domain.AccessCode;
import domain.Classroom;
import domain.BoBAction;
import domain.Box;
import domain.Session;
import domain.GroupOperation;
import domain.PersistMode;
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
import domain.managers.IManageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistence.repositories.AccessCodeRepository;
import persistence.repositories.CategoryRepository;

/**
 *
 * @author geers
 */
public class PersistenceController {
    private Map<String,IGenericRepository> repositories;
    private PersistMode mode;
    public PersistenceController()
    {
        repositories = new HashMap<>();
        repositories.put(BoBAction.class.getSimpleName(), new ActionRepository());
        repositories.put(Box.class.getSimpleName(),new BoxRepository());
        //category?
        repositories.put(Category.class.getSimpleName(), new CategoryRepository());
        repositories.put(Classroom.class.getSimpleName(), new ClassroomRepository());
        repositories.put(Exercise.class.getSimpleName(),  new ExerciseRepository());
        repositories.put(GroupOperation.class.getSimpleName(), new GroupOperationRepository());
        repositories.put(Group.class.getSimpleName(),new GroupRepository());
        repositories.put(Session.class.getSimpleName(),new SessionRepository());
        repositories.put(Student.class.getSimpleName(),new StudentRepository());
        repositories.put(AccessCode.class.getSimpleName(), new AccessCodeRepository());
        //put other repos
        mode = PersistMode.NEW;
        
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
    
    public void setPersistMode(PersistMode mode) {
        this.mode = mode;
    }
    public PersistMode getPersistMode() {
        return mode;
    }
    public <T> void persistObject(Class<T> type, T object) {
        IGenericRepository repository = repositories.get(type.getSimpleName());
        if(mode == PersistMode.NEW) {
         ((IManageable) object).setId(0);
          repository.persistObject(object);  
        }
        else if(mode == PersistMode.UPDATE) {
            repository.update(object);
        }
            
    }
    public <T> void deleteObject(Class<T> type, T object) {
       IGenericRepository repository = repositories.get(type.getSimpleName());
       repository.delete(object);
    }
    
    public boolean boxExists(String name) {
        return ((BoxRepository) repositories.get(Box.class.getSimpleName())).contains(name);
    }
}
