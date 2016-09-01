package cn.smbms.service.provider;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.dao.provider.ProviderDaoImpl;
import cn.smbms.pojo.bill.Bill;
import cn.smbms.pojo.provider.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;

import java.sql.Connection;
import java.util.List;

/**
 * Created by alren on 16-8-25.
 */
public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao = null;

    public ProviderServiceImpl() {
        providerDao = new ProviderDaoImpl();
    }

    @Override
    public List<Provider> getProviderList(Provider provider) {
        List<Provider> providerList = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            providerList = providerDao.getProviders(connection, provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseDao.closeResouce(connection, null, null);

        return providerList;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            if (providerDao.modifyProvider(connection, provider) > 0)  {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }


        return flag;
    }

    /*业务，根据ID删除供应商表的数据之前，需要先去订单表里进行查询订单操作
    若订单表中无该供应商的订单数据，则可以删除
    若有，则不可以删除
    * */
    @Override
    public boolean deleteProvider(int proId) {
        boolean flag = false;
        Connection connection = null;
        BillService billService = null;
        List<Bill> billList = null;

        //删除之前先查询供应商表中是否存在账单
        billService =  new BillServiceImpl();
        billList = billService.getBillByProviderId(proId);

        if (billList != null) {
            if (billList.size() > 0) { //查询成功,且存在订单，不可以删除
                flag = false;
            } else {
                try {
                    connection = BaseDao.getConnetion();
                    connection.setAutoCommit(false); //开启事务
                    if (providerDao.deleteProvider(connection, proId) > 0) {
                        connection.commit();
                        flag = true;
                    }
                }catch(Exception e) {
                    try {
                        connection.rollback(); //若出现异常，回滚
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                } finally {
                    BaseDao.closeResouce(connection, null, null);
                }
            }
        } else {
                flag = false;
        }
        return flag;
    }

    @Override
    public boolean addProvider(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            if (providerDao.addProvider(connection, provider) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return flag;
    }
}
