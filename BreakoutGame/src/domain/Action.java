/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author geers
 */
@Entity
public class Action implements IManageable, Serializable {

    public Action()
    {
    }

    
    @Id
    private long id;

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
