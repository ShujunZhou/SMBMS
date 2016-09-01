package cn.smbms.dao.user;

import cn.smbms.pojo.user.User;

import java.sql.Connection;
import java.util.List;

/**
 * Created by alren on 16-8-18.
 */
public interface UserDao {
    public int add(Connection connection, User user) throws Exception;
    public User getLoginUser(Connection connection, String userCode) throws Exception;
    public List<User> getUserList(Connection connection, String userName) throws Exception;
    public User findUser(Connection connection, int userId) throws Exception;
    public int updateUser(Connection connection, User user) throws Exception;
    public int deleteUser(Connection connection, int userId) throws Exception;
    public int updatePwd(Connection connection, String newPwd, int userId ) throws Exception;
}