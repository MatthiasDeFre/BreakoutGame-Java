/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.groupOperationBehaviours.AnwserBehaviour;
import domain.groupOperationBehaviours.AnwserBehaviourFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Matthias
 */
public class Assignment {
    
    private int referenceNr;
    private Exercise exercise;
    private GroupOperation groupOperation;
    private int accessCode;
    private AnwserBehaviour anwserBehaviour;
    public Assignment(int referenceNr, Exercise exercise, GroupOperation groupOperation, int accessCode)
    {
        this.referenceNr = referenceNr;
        this.groupOperation = groupOperation;
        this.exercise = exercise;
        this.accessCode = accessCode;
        anwserBehaviour = AnwserBehaviourFactory.createAnwserBehaviour(groupOperation.getCategory());
    }
    
    public String getAnwser() {
        return anwserBehaviour.getAnwser(exercise.getAnswer(), groupOperation.getValueString());
    }

    public int getReferenceNr()
    {
        return referenceNr;
    }

    public void setReferenceNr(int referenceNr)
    {
        this.referenceNr = referenceNr;
    }

    public Exercise getExercise()
    {
        return exercise;
    }

    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
    }

    public GroupOperation getGroupOperation()
    {
        return groupOperation;
    }

    public void setGroupOperation(GroupOperation groupOperation)
    {
        this.groupOperation = groupOperation;
    }

    public int getAccessCode()
    {
        return accessCode;
    }

    public void setAccessCode(int accessCode)
    {
        this.accessCode = accessCode;
    }

    public AnwserBehaviour getAnwserBehaviour()
    {
        return anwserBehaviour;
    }

    public void setAnwserBehaviour(AnwserBehaviour anwserBehaviour)
    {
        this.anwserBehaviour = anwserBehaviour;
    }
    
    
    
}
