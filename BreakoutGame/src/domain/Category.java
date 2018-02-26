/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.LangConfig;

/**
 *
 * @author Alexander
 */
@Entity
public class Category implements Serializable {
    
//  
//    MATH("MATH"),
//    GEOGRAPHY("GEOGRAPHY"),
//    DUTCH("DUTCH");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    
    protected Category() {
        
    }
    public Category(String name)    
    {
        setName(name);
    }
    public void setName(String name) {
        if(name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException();
        }
        
    }
    public String getDescription() {
        return LangConfig.getString(name);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        return true;
    }
    
    
}
