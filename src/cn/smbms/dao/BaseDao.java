package cn.smbms.dao;

import cn.smbms.tools.ConfigManager;

import java.sql.*;

/**
 * Created by alren on 16-8-18.
 */
public class BaseDao {
    //获取数据库连接
    public static Connection getConnetion() {
        Connection connection = null;
        String driver = ConfigManager.getInstance().getValue("driver");
        String url = ConfigManager.getInstance().getValue("url");
        String user = ConfigManager.getInstance().getValue("user");
        String password = ConfigManager.getInstance().getValue("password");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    //查询操作(向上抛出异常,交给事物层处理)
    public static ResultSet execute (Connection connection, ResultSet rs, PreparedStatement pstm,
                                   String sql, Object[] params) throws Exception{
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }
        rs = pstm.executeQuery();

        return rs;
    }

    //更新操作
    public static int execute (Connection connection, PreparedStatement pstm,
                               String sql, Object[] params) throws Exception{
        int updateRows;
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }
        updateRows = pstm.executeUpdate();

        return updateRows;
    }


    //释放资源
    public static boolean closeResouce(Connection connection, PreparedStatement pstm, ResultSet rs) {
        boolean flag = true;

        if (rs != null) {
            try {
                rs.close();
                rs = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if (pstm != null) {
            try {
                pstm.close();
                pstm = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag =false;
            }
        }

        return flag;
    }

}
