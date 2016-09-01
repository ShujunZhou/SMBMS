package cn.smbms.dao.provider;

import cn.smbms.pojo.provider.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * Created by alren on 16-8-25.
 */
public interface ProviderDao {
    //查询供应商列表，将前台的发送的数据封装到Provider对象中，增加灵活性
    public List<Provider> getProviders(Connection connection, Provider provider) throws Exception;
    //根据id修改用户信息
    public int modifyProvider(Connection connection, Provider provider) throws Exception;
    //根据id删除供应商
    public int deleteProvider(Connection connection, int proId) throws Exception;
    //add provider
    public int addProvider(Connection connection, Provider provider) throws Exception;
}
