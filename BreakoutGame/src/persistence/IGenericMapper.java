/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.List;

/**
 *
 * @author Matthias
 */
public interface IGenericMapper<T> {
    List<T> getAll();
    T getById(long id);
    T update(T object);
    void delete(T object);
    void persistObject(T object);
    boolean exists(long id);
}
