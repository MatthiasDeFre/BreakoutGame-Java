/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private long id;
    private String answer;
    private String feedback;
    private String assignment;
    private Category category;
    
    @ManyToMany
    private List<GroupOperation> groupOperations;

  //  GroupOperation groupOperation;
    
    public Exercise(String answer, String feedback, String assignment, Category category) {
        this(answer, feedback, assignment, category, new ArrayList<>());
    }
    public Exercise(String answer, String feedback, String assignment, Category category, List<GroupOperation> operations) {
        setAnswer(answer);
        setFeedback(feedback);
        setAssignment(assignment);
        setCategory(category);
        groupOperations = operations;
    }
    

    protected Exercise() {
    }
    
  
    
    public boolean hasFeedback() {
        return !(feedback == null) && !(feedback.isEmpty());
    }

    public String getAnswer()
    {
        return answer;
    }

    public String getFeedback()
    {
        return feedback;
    }

    public String getAssignment()
    {
        return assignment;
    }

    public Category getCategory()
    {
        return category;
    }

    public List<GroupOperation> getGroupOperations()
    {
        return groupOperations;
    }

    
    
    public void setAnswer(String answer)
    {
        if(answer == null ||answer.trim().equals(""))
            throw new IllegalArgumentException();
        else
            this.answer = answer.trim();
    }

    public void setFeedback(String feedback)
    {   
        if(feedback != null)
         this.feedback = feedback.trim();
        else
         this.feedback = feedback;
    }

    public void setAssignment(String assignment)
    {
        if(assignment == null || assignment.trim().equals(""))
              throw new IllegalArgumentException();
        else
            this.assignment = assignment.trim();
    }

    public void setCategory(Category category)
    {   
        if(category == null)
            throw new IllegalArgumentException();
        else
           this.category = category;
    }
    
    public String getCategoryDescription() {
        return category.getDescription();
    }
    
    
    @Override
    public String toString()
    {
        return String.format("%s %s", category.getDescription(), assignment);
    }
    
}
