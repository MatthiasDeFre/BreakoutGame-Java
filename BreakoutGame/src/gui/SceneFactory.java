/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.BoxController;
import domain.ListStudentController;
import domain.SceneName;
import static domain.SceneName.*;
import domain.ExerciseDomainController;
import gui.ComplexApplication2.ExerciseController;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Alexander
 */
public class SceneFactory {
    private final Map<SceneName, Supplier<Parent>> factory = new HashMap();

    public SceneFactory(ExerciseDomainController dc, BoxController dc2) {
        add(MAINSCREEN,()-> new MainScreenController(dc));
        add(STARTSCREEN, () -> new StartScreenController(dc));
        add(EXERCISEMAINSCREEN2, () -> new ExerciseController(dc));
        add(BOXSCREEN, () ->new BoxStartScreenController(dc2));
        
    }
    
    public SceneFactory(ListStudentController dc)
    {
     //   add(STUDENTSSCREEN, () -> new Scene(new ListStudentsController(dc)));
    }
    
    private void add(SceneName sn, Supplier<Parent> scene) {
        factory.put(sn, scene);
    }
    
    public Parent createScene(SceneName sn) {
        Supplier<Parent> scene = factory.get(sn);
        return scene != null ? scene.get() : null;
    }
    
    
}
