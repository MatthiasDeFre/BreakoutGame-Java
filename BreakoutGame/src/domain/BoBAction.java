/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author geers
 */
@Entity
public class BoBAction implements IManageable, Serializable {

    private SimpleStringProperty name = new SimpleStringProperty();
    public BoBAction()
    {
    }

    public BoBAction(String name)
    {
        setName(name);
    }

    @Column(name="name")
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }
    
    
    
    
    
    private long id;

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
        this.id = id;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
}
