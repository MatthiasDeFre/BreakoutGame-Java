/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.SceneName;
import static domain.SceneName.*;
import domain.UseCaseExerciseAdminController;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javafx.scene.Scene;

/**
 *
 * @author Alexander
 */
public class SceneFactory {
    private final Map<SceneName, Supplier<Scene>> factory = new HashMap();

    public SceneFactory(UseCaseExerciseAdminController dc) {
        add(STARTSCREEN, () -> new Scene(new StartScreenController(dc)));
        add(EXERCISESCREEN, () -> new Scene(new ExerciseDetailScreenController(dc)));
        add(EXERCISEMAINSCREEN, () -> new Scene(new ExerciseController(dc)));
    }
    
    private void add(SceneName sn, Supplier<Scene> scene) {
        factory.put(sn, scene);
    }
    
    public Scene createScene(SceneName sn) {
        Supplier<Scene> scene = factory.get(sn);
        return scene != null ? scene.get() : null;
    }
    
    
}
