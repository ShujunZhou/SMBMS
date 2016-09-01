package cn.smbms.service.bill;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.bill.BillDao;
import cn.smbms.dao.bill.BillDaoImpl;
import cn.smbms.pojo.bill.Bill;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alren on 16-8-27.
 */
public class BillServiceImpl implements BillService {
    BillDao billDao = new BillDaoImpl();
    @Override
    public List<Bill> getBillByProviderId(int providerId) {
        List<Bill> billList = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            billList = billDao.getBillByProviderId(connection, providerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResouce(connection, null, null);
        }

        return billList;
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
        List<Bill> billList = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            billList = billDao.getBillList(connection, bill);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return billList;
    }

    @Override
    public boolean addBill(Bill bill) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnetion();
            if (billDao.addBill(connection, bill) > 0) {
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
