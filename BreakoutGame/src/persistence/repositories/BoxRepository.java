/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Box;

/**
 *
 * @author geers
 */
public class BoxRepository extends GenericRepository<Box> {

    public BoxRepository()
    {
        this(Box.class);
    }
    
    private BoxRepository(Class<Box> type) {
        super(type);
    }
}
