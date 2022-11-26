// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com/*

package dao;

import entity.Book;
import entity.Book_Author;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s
 */
public interface BookDAO extends BaseDAO<Book>{
    List<Book> getAllBook();
    Book getBookbyId(int id);
    List<Book_Author> getBookListWithAuthor(int index, int perPage);
    int countBook();
    public int countBookByCategory(int cid);
    public int countBooksSearched(String search);
    public List<Book_Author> getBookListWithAuthor();
    public ArrayList<Book_Author> getBookWithAuthorByCategory(int cid, int index, int perPage);
    public ArrayList<Book_Author> getSearchedBookListWithAuthor(String search, int index, int perPage);
    public void updateBook(Book book);
}
