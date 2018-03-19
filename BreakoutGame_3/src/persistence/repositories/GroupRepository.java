/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import javafx.scene.Group;

/**
 *
 * @author geers
 */
public class GroupRepository extends GenericRepository<Group> {
    
    public GroupRepository()
    {
        this(Group.class);
    }
            
    
    private GroupRepository(Class<Group> type) {
        super(type);
    }
    
}
