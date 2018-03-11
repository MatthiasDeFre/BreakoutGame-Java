/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author geers
 */
public class Session implements IManageable {
    
    private long id;
    private Box box;
    private List<Group> groups;
    private String description;
    private StudentClass classRoom;
    private String name;

    public Session(Box box, List<Group> groups, String description, StudentClass classRoom, String name)
    {
        this.box = box;
        this.groups = groups;
        this.description = description;
        this.classRoom = classRoom;
        this.name = name;
    }
    
    private int counterAccessCode;
    public void generatePaths() {
        groups.stream().forEach(e -> {
            List<Assignment> assignments = new ArrayList<>();
            List<Exercise> shuffledExercises = new ArrayList<>(box.getExercises());
            Collections.shuffle(shuffledExercises);
            IntStream.rangeClosed(1, shuffledExercises.size()).forEach(e2 -> {
                List<GroupOperation> shuffledGroupOperations = new ArrayList<>(shuffledExercises.get(e2-1).getGroupOperations());
                Collections.shuffle(shuffledGroupOperations);
                if(e2 < shuffledExercises.size())
                     assignments.add(new Assignment(e2, shuffledExercises.get(e2-1),shuffledGroupOperations.get(0), box.getAccessCodes().get(counterAccessCode++)));
                else
                     assignments.add(new Assignment(e2, shuffledExercises.get(e2-1),shuffledGroupOperations.get(0), null));  
            });
            Path path = new Path(assignments, box.getActions());
            e.setPath(path);
        });
    }
    
    
    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public void setId(long id)
    {
        this.id = id;
    }
    
    public void testToString() {
        groups.forEach(e -> e.testToString());
    }
    
}
