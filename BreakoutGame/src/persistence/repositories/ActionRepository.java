/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Action;

/**
 *
 * @author geers
 */
public class ActionRepository extends GenericRepository<Action> {
    
    public ActionRepository()
    {
        this(Action.class);
    }
    
    private ActionRepository(Class<Action> type) {
        super(type);
    }
    
}
