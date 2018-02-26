/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Action;
import domain.Exercise;

/**
 *
 * @author geers
 */
public class ActionRepository extends GenericRepository<Action> {
    
    public ActionRepository(Class<Action> type) {
        super(type);
    }
    
}
