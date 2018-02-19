/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.LangConfig;

/**
 *
 * @author Alexander
 */
public enum Category {
    
  
    MATH("MATH"),
    GEOGRAPHY("GEOGRAPHY"),
    DUTCH("DUTCH");
    private final String key;
    Category(String key)    
    {
        this.key = key;
    }
    String getDescription() {
        return LangConfig.getString(key);
    }
}
