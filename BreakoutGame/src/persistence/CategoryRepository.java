/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Category;
import java.util.ArrayList;
import java.util.List;
import persistence.GenericRepository;

/**
 *
 * @author geers
 */
public class CategoryRepository extends GenericRepository<Category>
{

    public CategoryRepository(Class<Category> type) {
        super(type);
    }
    
    public CategoryRepository()
    {
        super(Category.class);
    }
    
    
    
    
    
}
