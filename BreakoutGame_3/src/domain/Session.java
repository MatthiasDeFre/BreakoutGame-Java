/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author geers
 */
@javax.persistence.Entity
@Table(name = "BoBSession")
public class Session implements IManageable, Serializable {
    
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
       
    @ManyToOne
    private Box box;
   
   @OneToMany(cascade = CascadeType.ALL)
    private List<Group> groups;
    private String description;

    private StudentClass classRoom;
    private String name;
   

   
   private LocalDate activationDate;
    private boolean tile;
    private boolean feedback;

    private SessionStatus sessionStatus;
    
    protected Session()
    {
    }

    public Session(Box box, List<Group> groups, String description, StudentClass classRoom, String name, LocalDate activationDate, boolean tile, boolean feedback)
    {
        this.box = box;
       this.groups = groups;
        this.description = description;
   this.classRoom = classRoom;
      this.name = name;
       setActivationDate(activationDate);
        setFeedback(feedback);
        this.tile = tile;
        sessionStatus = SessionStatus.SCHEDULED;
    
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

 
    public LocalDate getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate)
    {
        if(!activationDate.isEqual(LocalDate.now()) && activationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Datum ligt voor vandaag");
        }
        this.activationDate = activationDate;
    }

    public boolean isFeedback()
    {
        return feedback;
    }

    public void setFeedback(boolean feedback)
    {
        if(this.tile && !feedback)
            throw new IllegalArgumentException("Afstandsonderwijs kan alleen maar feedback krijgen");
        this.feedback = feedback;
    }

    public Box getBox()
    {
        return box;
    }

    public void setBox(Box box)
    {
        this.box = box;
    }
    
    
    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(List<Group> groups)
    {
        this.groups = groups;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

   
    public StudentClass getClassRoom()
    {
        return classRoom;
    }

    public void setClassRoom(StudentClass classRoom)
    {
        this.classRoom = classRoom;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isTile()
    {
        return tile;
    }

    public void setTile(boolean tile)
    {
        this.tile = tile;
    }
    
    
    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
    
    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
  /*  @Transient
   public void testToString() {
        groups.forEach(e -> e.testToString());
    }*/
    
   
    private int counterAccessCode;
}
