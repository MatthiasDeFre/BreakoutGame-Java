/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author geers
 */
@Entity
@Table(name="Exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    String answer;
    String feedback;
    String assignment;
    Category category;
    FilteredList<GroupOperation> groupOperations;
    
    @ManyToOne
    GroupOperation groupOperation;
    
    public Exercise(String answer, String feedback, String assignment, Category category) {
        this.answer = answer;
        this.feedback = feedback;
        this.assignment = assignment;
        this.category = category;
    }
    

    protected Exercise() {
    }
    
    
}
