/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.ChangeTracking;

/**
 *
 * @author geers
 */
@Entity
@Table(name = "Exercises")
public class Exercise implements IManageable, Serializable {
    
    private long id;
    //   @Column(unique = true)
    private SimpleStringProperty name = new SimpleStringProperty();
    private String answer;
    private String feedback;
    private SimpleStringProperty assignment = new SimpleStringProperty();
    
    private SimpleObjectProperty<Category> category = new SimpleObjectProperty<>();
    
    private List<GroupOperation> groupOperations;

    /*   @Transient
    private ObservableList<GroupOperation> groupOperationsTemp;*/
    //  GroupOperation groupOperation;
    public Exercise(String name, String answer, String feedback, String assignment, Category category)
    {
        this(name, answer, feedback, assignment, category, new ArrayList<>());
    }
    
    public Exercise(String name, String answer, String feedback, String assignment, Category category, List<GroupOperation> operations)
    {
        setName(name);
        setAnswer(answer);
        setFeedback(feedback);
        setAssignment(assignment);
        setCategory(category);
        groupOperations = new ArrayList<>(operations);
        //     groupOperationsTemp = FXCollections.observableArrayList(groupOperations);
    }

    //Creating / copying
    public void copy(Exercise exercise)
    {
        setName(exercise.getName());
        setAnswer(exercise.getAnswer());
        setFeedback(exercise.getFeedback());
        setAssignment(exercise.getAssignment());
        setCategory(exercise.getCategory());
        groupOperations = new ArrayList<>(exercise.getGroupOperations());
        // groupOperationsTemp = FXCollections.observableArrayList(groupOperations);
    }
    
    public Exercise()
    {
        category.set(new Category("STANDARD"));
        groupOperations = new ArrayList<>();
    }
    
    public Exercise(Exercise ex)
    {
        copy(ex);
    }
    
    public boolean hasFeedback()
    {
        return !(feedback == null) && !(feedback.isEmpty());
    }

    @Column(name = "name")
    public String getName()
    {
        return name.get();
    }
    
    @Column(name = "answer")
    public String getAnswer()
    {
        return answer;
    }

    @Column(name = "feedback")
    public String getFeedback()
    {
        return feedback;
    }

    @Column(name = "assignment")
    public String getAssignment()
    {
        return assignment.get();
    }

    @ManyToOne
    @JoinColumn(name = "category")
    public Category getCategory()
    {
        return category.get();
    }

    @ManyToMany
    @JoinColumn(name = "groupoperations")
    public List<GroupOperation> getGroupOperations()
    {
        return groupOperations;
    }
    
    public void setGroupOperations(List<GroupOperation> groupOperations)
    {
        this.groupOperations = groupOperations;
        
    }

    public void setName(String name)
    {
        if (name == null || name.trim().equals(""))
        {
            throw new IllegalArgumentException();
        } else
        {
            this.name.set(name);
        }
        
    }
    
    public void setAnswer(String answer)
    {
        if (answer == null || answer.trim().equals(""))
        {
            throw new IllegalArgumentException();
        } else
        {
            this.answer = answer.trim();
        }
    }
    
    public void setFeedback(String feedback)
    {
        if (feedback != null && !feedback.trim().equals(""))
        {
            this.feedback = feedback.trim();
        } else
        {
            this.feedback = null;
        }
    }
    
    public void setAssignment(String assignment)
    {
        if (assignment == null || assignment.trim().equals(""))
        {
            throw new IllegalArgumentException();
        } else
        {
            this.assignment.set(assignment.trim());
        }
    }
    
    public void setCategory(Category category)
    {
        if (category == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            this.category.set(category);
        }
    }
    
    public String getCategoryDescription()
    {
        return category.get().getDescription();
    }
    
    @Override
    public String toString()
    {
        return String.format("%s %s", category.get().getDescription(), assignment);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId()
    {
        return id;
    }
    
    @Override
    public void setId(long id)
    {
        System.out.println(id);
        this.id = id;
    }
    
    public StringProperty assignmentProperty()
    {
        return assignment;
    }
    
    public ObjectProperty categoryProperty()
    {
        return category;
    }
    
}
