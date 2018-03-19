 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.BoxManager;
import domain.managers.GroupManager;
import domain.managers.IManageable;
import domain.managers.Manager;
import domain.managers.SessionManager;
import domain.managers.StudentManager;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class SessionController {
    
    
    private SessionManager sessionManager;
    private GroupManager groupManager;
    private BoxManager boxManager;
    private Map<String, Manager> managers;

    public SessionController(PersistenceController persistenceController)
    {
                
        sessionManager = new SessionManager(persistenceController);
        groupManager = new GroupManager(persistenceController);
        boxManager = new BoxManager(persistenceController);
        managers = new HashMap<>();

        managers.put(Session.class.getSimpleName(), sessionManager);
        managers.put(Box.class.getSimpleName(), boxManager);
   // STUDENT / STUDENTCLASS MANAGER
   //managers.put(StudentClass.class.getSimpleName(), new Stu)
        managers.put(GroupManager.class.getSimpleName(), groupManager);
    }
    
    public FilteredList getFilteredItems(Class<? extends IManageable> className) {
        return managers.get(className.getSimpleName()).getFilteredItems();
    }
    
    public void addObserver(Class<? extends IManageable> className, Observer obs) {
        managers.get(className.getSimpleName()).addObserver(obs);
    }
    
    public void saveSession(String description, String name, LocalDate activationDate, boolean tile, boolean feedback) {
     //TODO NEED OTHER MANAGERS => group, studentclass etc
          managers.get(Session.class.getSimpleName()).save(new Session(
                (Box) managers.get(Box.class.getSimpleName()).getSelected(), 
                sessionManager.getTempGroups(),
                description, 
                new StudentClass(), 
                name, 
                activationDate, 
                tile, 
                feedback));
    }
    
    public void addNewTempGroup(String name) {
        //TODO GET TEMP STUDENTS FROM MANAGER
        sessionManager.getTempGroups().add(new BoBGroup(name));
    }
    public void removeTempGroup(BoBGroup group) {
        sessionManager.getTempGroups().remove(group);
      
    }
      
    public void addStudentToTempGroup(Student student) {
       ((BoBGroup) groupManager.getSelected()).addStudent(student);
    }  
    public void removeStudentFromTempGroup(Student student) {
        ((BoBGroup) groupManager.getSelected()).removeStudent(student);
    }
    
    public void generateGroups(int amount, boolean notEmpty) {
        List<BoBGroup> groups;
        if(notEmpty)
           groups = GroupManager.generateRandomGroups(new StudentClass(), amount);
        else
           groups = GroupManager.generateEmptyGroups(amount);
        sessionManager.clearTempGroups();
        sessionManager.addAllToTempGroup(groups);
    }
    
    public void generatePaths() {
        SessionManager.generatePaths(sessionManager.getTempGroups(), boxManager.getSelected());
    }
    
    
    
}
