/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.AccessCode;
import javafx.collections.FXCollections;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class AccessCodeManager extends Manager<AccessCode>{

    protected AccessCodeManager()
    {
        super(AccessCode.class, new PersistenceController());
    }
    
    
    
    public AccessCodeManager(PersistenceController persistenceController)
    {
        super(AccessCode.class, persistenceController);
        setItems(FXCollections.observableArrayList(persistenceController.getAllOfType(AccessCode.class)));
        
    }
    
}
