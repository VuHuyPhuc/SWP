// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface BaseDAO<T> {
    T get(int id);
    
    List<T> getAll();
    
    int insert(T t);
    
    int update(T t);
}