// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao;

import entity.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 *
 * @author s
 */
public interface AuthorDAO extends BaseDAO<Author>{
    List<Author> getAllAuthor();
    Author getAuthorById(int id);
    Author getAuthorByBook(int bookid);
    int getNumberPage();
    ArrayList<Author> pagingAuthor(int index);
}
