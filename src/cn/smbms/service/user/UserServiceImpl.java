package cn.smbms.service.user;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.user.UserDao;
import cn.smbms.dao.user.UserDaoImpl;
import cn.smbms.pojo.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alren on 16-8-19.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = null;

    public UserServiceImpl() {
        userDao = new UserDaoImpl(); //延迟加载
    }

    @Override
    public boolean add(User user) {
        boolean flag = false;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = BaseDao.getConnetion();
            connection.setAutoCommit(false); //开启事物管理
            if (userDao.add(connection, user) > 0) {
                connection.commit();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();// 如果增加失败，就需要回滚
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return flag;
    }

    @Override
    public User login(String userCode, String userPassword) {
        User user = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }
        //密码的比对
        if (null != user) {
            if (!user.getUserPassword().equals(userPassword)) {
                user = null;
            }
        }

        return user;
    }

    @Override//查询用户列表
    public List<User> getUserList(String userName) {
        List<User> userList = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            userList = userDao.getUserList(connection, userName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return userList;
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        User user = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }
        return user;
    }

    @Override
    public User findUser(int userId) {
        User user = null;
        Connection connection = null;
        try {
            connection = BaseDao.getConnetion();
            user = userDao.findUser(connection, userId);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            connection.setAutoCommit(false);

            if (userDao.updateUser(connection, user) > 0) {
                connection.commit();
                flag = true;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return flag;
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            connection.setAutoCommit(false);
            if (userDao.deleteUser(connection, userId) > 0) {
                connection.commit();
                flag = true;
            }
        } catch(Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return flag;
    }

    @Override
    public boolean updatePwd(String newPwd, int userId) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            connection.setAutoCommit(false);
            if (userDao.updatePwd(connection, newPwd, userId) > 0) {
                connection.commit();
                flag = true;
            }
        } catch(Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return flag;
    }
}
