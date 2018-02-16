/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Matthias
 */

public class GroupOperation {
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

    public void setValue(String value)
    {
        this.value = value;
    }
    
}
