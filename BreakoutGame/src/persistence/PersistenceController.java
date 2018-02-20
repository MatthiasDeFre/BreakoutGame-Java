/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Exercise;
import java.util.List;

/**
 *
 * @author geers
 */
public class PersistenceController {
    private ExerciseRepository exerciseRepository;

    public PersistenceController()
    {
        exerciseRepository = new ExerciseRepository();
    }
    
    
    public List<Exercise> getListAllExercises()
    {
        return exerciseRepository.getAll();
    }
}
