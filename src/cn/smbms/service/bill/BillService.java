package cn.smbms.service.bill;

import cn.smbms.pojo.bill.Bill;

import java.util.List;

/**
 * Created by alren on 16-8-27.
 */
public interface BillService {
    public List<Bill> getBillByProviderId(int providerId);
    public List<Bill> getBillList(Bill bill);
    public boolean addBill(Bill bill);
}
