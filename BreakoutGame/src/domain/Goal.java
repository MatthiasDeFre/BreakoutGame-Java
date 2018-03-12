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
 * @author Matthias
 */
@Entity
public class Goal implements Serializable, IManageable {

    @Id
    private long id;

    private String code;
    public Goal()
    {
    }

    public Goal(String code)
    {
        this.code = code;
    }

    
    
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
