/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Matthias
 */
@Entity
@Table(name="GroupOperation")
public class GroupOperation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private OperationCategory category;
    private String value;

    //Constructors
    /**
     * Standard constructor
     * Used for JPA
     */
    protected GroupOperation() {
        
    }
    
    public GroupOperation(OperationCategory category, String value)
    {
        this.category = category;
        this.value = value;
    }
    //Getters and setters
    public OperationCategory getCategory()
    {
        return category;
    }

    public void setCategory(OperationCategory category)
    {
        this.category = category;
    }

    public String getValue()
    {
        return value;
    }
    public String getDescription() {
        return String.format(category.getDescription(), value.split("&"));
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    
}
