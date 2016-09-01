package cn.smbms.dao.user;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alren on 16-8-19.
 */
public class UserDaoImpl implements UserDao{

    @Override
    public int add(Connection connection, User user) throws Exception {
        int updateRows = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "insert into smbms_user(userCode, userName, userPassword, gender, " +
                    "birthday, phone, address, userType, createBy, creationDate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = new Object[] {user.getUserCode(), user.getUserName(),
                    user.getUserPassword(), user.getGender(), user.getBirthday(),
                    user.getPhone(), user.getAddress(), user.getUserType(),
                    user.getCreateBy(), user.getCreationDate()};

            updateRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResouce(null, pstm, null);
        }

        return updateRows;
    }

    @Override //查询登陆者的信息
    public User getLoginUser(Connection connection, String userCode) throws Exception {
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        //需要判断connection是否为空
        if (null != connection) {
            String sql = "select id, userCode, userName, userPassword, " +
                    "gender,  birthday, phone, address, userType, createBy, " +
                    "creationDate, modifyBy, modifyDate from smbms_user where userCode = ?";
            Object[] params = {userCode};
            rs = BaseDao.execute(connection, rs, pstm, sql, params);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserType(rs.getInt("userType"));
                user.setCreateBy(rs.getInt("createBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));//java获取取得Timestamp类型的当前系统时间
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
        }
        BaseDao.closeResouce(null, pstm, rs);

        return user;
    }

    @Override//实现模糊查询
    public List<User> getUserList(Connection connection, String userName) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        List<User> userList = new ArrayList<User>();

        if (connection != null) {
            String sql = "select id, userCode, userName, gender, birthday, phone, userType " +
                    "from smbms_user where userName like ?";
            Object[] params = {"%" + userName + "%"};
            rs = BaseDao.execute(connection, rs, pstm, sql, params);

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserType(rs.getInt("userType"));
                user.setAge();//获取年龄

                userList.add(user);
            }
            BaseDao.closeResouce(null, pstm, rs);
        }

        return userList;
    }

    @Override
    public User findUser(Connection connection, int userId) throws Exception {
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (connection != null) {
            String sql = "select id, userCode, userName, userPassword, " +
                    "gender,  birthday, phone, address, userType from smbms_user where id = ?";
            Object[] params = {userId};
            rs = BaseDao.execute(connection, rs, pstm, sql, params);

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserType(rs.getInt("userType"));
            }
            BaseDao.closeResouce(null, pstm, rs); //关闭资源
        }

        return user;
    }

    @Override
    public int updateUser(Connection connection, User user) throws Exception {
        int updateRows = 0;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "update smbms_user set userName = ?, gender = ?," +
                        "birthday = ?, phone = ?, address = ?, userType = ?, " +
                    "modifyBy = ?, modifyDate = ?  where id = ?";
            Object[] params = {user.getUserName(), user.getGender(),
                        user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserType(),
                    user.getModifyBy(), user.getModifyDate(), user.getId()};
            updateRows = BaseDao.execute(connection, pstm, sql, params);

            BaseDao.closeResouce(null, pstm, null);
        }

        return updateRows;
    }

    @Override
    public int deleteUser(Connection connection, int userId) throws Exception {
        int deleteRows = 0;
        PreparedStatement pstm = null;

        if(connection != null) {
            String sql = "delete from smbms_user where id = ?";
            Object[] params = {userId};

            deleteRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResouce(null, pstm, null);
        }

        return deleteRows;
    }

    @Override
    public int updatePwd(Connection connection, String newPwd, int userId) throws Exception {
        int updateRows = 0;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object[] params = {newPwd, userId};

            updateRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResouce(null, pstm, null);
        }

        return updateRows;
    }
}
