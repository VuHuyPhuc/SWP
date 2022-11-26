
package dao;

import entity.User;
import java.util.List;

/**
 *
 * @author s
 */
public interface UserDAO extends BaseDAO<User> {

    List<User> userList();

    void register(String fullname, String username, String password, String email, String role, boolean status);

    User check(String username, String password);

    public int countUser();

    public List<User> getPaggedUsers(int index, int perPage);
    
    public User getByID(int id);
    
    public int updateUserStatus(int uid, boolean status);
    
}
