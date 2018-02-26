/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.util.Objects;
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
public class GroupOperation implements IManageable {
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.category);
        hash = 23 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final GroupOperation other = (GroupOperation) obj;
        if (!Objects.equals(this.value, other.value))
        {
            return false;
        }
        if (this.category != other.category)
        {
            return false;
        }
        return true;
    }
    
    public GroupOperation(OperationCategory category, String value)
    {
        this.category = category;
        this.value = value;
    }
    
    public void copy(GroupOperation groupOperation) {
        setCategory(groupOperation.getCategory());
        setValue(groupOperation.getValue());
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
    
}
