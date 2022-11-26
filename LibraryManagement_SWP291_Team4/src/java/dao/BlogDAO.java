// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao;

import entity.Blog;
import java.util.List;

/**
 *
 * @author s
 */
public interface BlogDAO extends BaseDAO<Blog>{
    List<Blog> getAllBlogs();
    Blog getBlogbyId(int id);
    List<Blog> pagingBlog(int index);
    int getNumberPage();
}
