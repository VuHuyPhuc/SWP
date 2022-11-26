// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao;

import entity.Category;
import entity.CategoryDisplayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s
 */
public interface CategoryDAO extends BaseDAO<Category>{
    List<Category> getAllCategory();
    public ArrayList<CategoryDisplayer> getAllCategoryDisplayer();
}
