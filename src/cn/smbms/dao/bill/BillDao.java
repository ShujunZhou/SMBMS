package cn.smbms.dao.bill;

import cn.smbms.pojo.bill.Bill;
import cn.smbms.pojo.provider.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * Created by alren on 16-8-27.
 */
public interface BillDao {
    public List<Bill> getBillByProviderId(Connection connection, int providerId) throws Exception;
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception; //tong yong de cha xun fang fa
    public int addBill(Connection connection, Bill bill) throws Exception;
}
