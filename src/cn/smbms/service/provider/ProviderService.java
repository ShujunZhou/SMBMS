package cn.smbms.service.provider;

import cn.smbms.pojo.provider.Provider;

import java.util.List;

/**
 * Created by alren on 16-8-25.
 */
public interface ProviderService {
    //查询供应商信息
    public List<Provider> getProviderList(Provider provider);
    //修改供应商信息
    public boolean modifyProvider(Provider provider);
    //删除供应商信息
    public boolean deleteProvider(int proId);
    //add provider
    public boolean addProvider(Provider provider);

}
