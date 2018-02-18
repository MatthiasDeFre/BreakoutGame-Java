/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author geers
 */
public class Exercise {
    String answer;
    String feedback;
    String assignment;
    Category category;
    FilteredList<GroupOperation> groupOperation;
    
    public Exercise(String answer, String feedback, String assignment, Category category) {
        this.answer = answer;
        this.feedback = feedback;
        this.assignment = assignment;
        this.category = category;
    }

    protected Exercise() {
    }
    
    
}
