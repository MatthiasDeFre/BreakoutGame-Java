/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Exercise;

/**
 *
 * @author Matthias
 */
public class ExerciseRepository extends GenericMapper<Exercise>{
    
    // <editor-fold defaultstate="collapsed" desc="Properties">

    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ExerciseRepository(Class<Exercise> type)
    {
        super(type);
    }
    
    
    // </editor-fold>
   
}
