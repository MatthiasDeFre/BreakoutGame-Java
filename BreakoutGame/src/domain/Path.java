/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Matthias
 */
public class Path {
    private List<Assignment> assignments;
    private List<BoBAction> actions;

    public Path(List<Assignment> assignments, List<BoBAction> actions)
    {
        this.assignments = assignments;
        this.actions = actions;
    }

    private int counter;
    public String getToStringTest() {
        StringBuilder path = new StringBuilder();
        assignments.forEach(e -> {
            path.append(e.getReferenceNr()).append(" ").append(e.getExercise().getAssignment()).append(" ").append(e.getGroupOperation().getDescription()).append(" ").append(e.getAnwser()).append(" ").append(actions.get(counter++).getName()).append(" ").append(e.getAccessCode() != null ? e.getAccessCode().getCode() : "Geen code nodig , laatste actie").append("\n");
            
        });
        return path.toString();
    }
    
    
    
}
