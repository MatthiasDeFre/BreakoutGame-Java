/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Matthias
 */

public class GroupOperation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private OperationCategory category;
    private int value;

    //Constructors
    /**
     * Standard constructor
     * Used for JPA
     */
    protected GroupOperation() {
        
    }
    
    public GroupOperation(OperationCategory category, int value)
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

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
    
}
