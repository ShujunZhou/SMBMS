package cn.smbms.dao.provider;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.provider.Provider;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alren on 16-8-25.
 */
public class ProviderDaoImpl implements ProviderDao {
    @Override  //获取供应商信息
    public List<Provider> getProviders(Connection connection, Provider provider) throws Exception {
        //必须将providerList设置成公共的，因为如果涉及多个条件查询时，保证数据不会丢失
        List<Provider> providerList = null;
        List listParams = new ArrayList();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, proCode, proName, proDesc, proContact, proPhone," +
                    "proAddress, proFax from smbms_provider where 1=1");
            if (null == provider) {
                providerList = getProviderList(connection, sql, new Object[]{});
            } else {
                if (provider.getId() > 0) {
                    sql.append(" and id like ? ");
                    listParams.add(provider.getId());
                }

                if (!StringUtils.isNullOrEmpty(provider.getProName())) {
                    sql.append(" and proName like ? ");
                    listParams.add("%" + provider.getProName() + "%");
                }

                if (!StringUtils.isNullOrEmpty(provider.getProCode())) {
                    sql.append(" and proCode like ?");
                    listParams.add("%" + provider.getProCode() + "%");
                }

                Object[] params = listParams.toArray();
                providerList =getProviderList(connection, sql, params);
            }
        }

        return providerList;
    }

    //抽象一个对于查询的方法，减少重复代码
    private List<Provider> getProviderList(Connection connection, StringBuffer sql,
                                           Object[] params)  throws Exception{
        List<Provider> providerList = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        rs = BaseDao.execute(connection, rs, pstm, sql.toString(), params);
        while (rs.next()) {
            Provider provider = new Provider();

            provider.setId(rs.getInt("id"));
            provider.setProCode(rs.getString("proCode"));
            provider.setProName(rs.getString("proName"));
            provider.setProDesc(rs.getString("proDesc"));
            provider.setProContact(rs.getString("proContact"));
            provider.setProPhone(rs.getString("proPhone"));
            provider.setProAddress(rs.getString("proAddress"));
            provider.setProFax(rs.getString("proFax"));

            providerList.add(provider);
        }
        BaseDao.closeResouce(null, pstm, rs);

        return providerList;
    }

    @Override
    public int modifyProvider(Connection connection, Provider provider) throws Exception {
        int updateRows = 0;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "update smbms_provider set proName = ?, proDesc = ?, proContact = ?," +
                    "proPhone = ?, proAddress = ?, proFax = ?, modifyBy = ?, modifyDate = ? " +
                    "where id = ?";
            Object[] params = {provider.getProName(), provider.getProDesc(), provider.getProContact(),
                    provider.getProPhone(), provider.getProAddress(), provider.getProFax(),
                    provider.getModifyBy(), provider.getModifyDate(), provider.getId()};

            updateRows = BaseDao.execute(connection, pstm, sql, params);
        }
        BaseDao.closeResouce(null, pstm, null);

        return updateRows;
    }

    @Override
    public int deleteProvider(Connection connection, int proId) throws Exception {
        int deleteRows = 0;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "delete from smbms_provider where id = ?";
            Object[] params = {proId};

            deleteRows = BaseDao.execute(connection, pstm, sql, params);
        }
        BaseDao.closeResouce(null, pstm, null);

        return deleteRows;
    }

    @Override
    public int addProvider(Connection connection, Provider provider) throws Exception {
        int addRows = 0;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "insert into smbms_provider(proCode, proName, " +
                    "proDesc, proContact, proPhone, proAddress, " +
                    "proFax, createdBy, creationDate) values(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            Object[] params = {provider.getProCode(), provider.getProName(),
                    provider.getProDesc(), provider.getProContact(), provider.getProPhone(),
                    provider.getProAddress(), provider.getProFax(), provider.getCreatedBy(),
            provider.getCreationDate()};
            try {
                connection = BaseDao.getConnetion();
                addRows = BaseDao.execute(connection, pstm, sql, params);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        BaseDao.closeResouce(null, pstm, null);

        return addRows;
    }
}
