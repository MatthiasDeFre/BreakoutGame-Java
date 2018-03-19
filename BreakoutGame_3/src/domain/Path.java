/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Matthias
 */
@Entity(name = "SessionPath")
public class Path implements Serializable {
   
    private List<Assignment> assignments;
    private List<BoBAction> actions;
    
    @Id
    private long id;

    public Path()
    {
    }

    public Path(List<Assignment> assignments, List<BoBAction> actions)
    {
        this.assignments = assignments;
        this.actions = actions;
    }

    private int counter;
    /*public String getToStringTest() {
        StringBuilder path = new StringBuilder();
        assignments.forEach(e -> {
            path.append(e.getReferenceNr()).append(" ").append(e.getExercise().getAssignment()).append(" ").append(e.getGroupOperation().getDescription()).append(" ").append(e.getAnwser()).append(" ").append(actions.get(counter++).getName()).append(" ").append(e.getAccessCode() != 0 ? e.getAccessCode() : "Geen code nodig , laatste actie").append("\n");
            
        });
        return path.toString();
    }*/

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    
    
}
