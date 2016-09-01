package cn.smbms.dao.bill;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.bill.Bill;
import cn.smbms.pojo.provider.Provider;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alren on 16-8-27.
 */
public class BillDaoImpl implements BillDao {
    @Override
    public List<Bill> getBillByProviderId(Connection connection, int providerId) throws Exception {
        List<Bill> billList = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "select id, billCode, productName, productDesc, productUnit, productCount," +
                    "totalPrice, isPayment, providerId from smbms_bill where providerId = ?";
            Object[] params = {providerId};
            billList = getBills(connection, rs, pstm, sql, params);

            BaseDao.closeResouce(null, pstm, rs);
        }

        return billList;
    }

    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
        List<Bill> billList = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        StringBuffer sql = new StringBuffer();
        sql.append("select id, billCode, productName, productDesc, productUnit," +
                "productCount, totalPrice, isPayment, providerId, creationDate from smbms_bill where 1=1");

        if (null != connection) {
            List list = new ArrayList();
            if (bill.getId() > 0) {
                sql.append(" and id = ? ");
                list.add(bill.getId());
            }
            if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
                System.out.println(bill.getProductName());
                list.add("%" + bill.getProductName() + "%");
            }
            if (!StringUtils.isNullOrEmpty(bill.getBillCode())) {
                sql.append(" and billCode like ? ");
                list.add(bill.getBillCode());
            }

            Object[] params = list.toArray();
            billList = getBills(connection, rs, pstm, sql.toString(), params);
        }
        return billList;
    }

    private List<Bill> getBills(Connection connection, ResultSet rs,
                                PreparedStatement pstm, String sql, Object[] params) throws Exception {
        List<Bill> billList = new ArrayList<>();
        rs = BaseDao.execute(connection, rs, pstm, sql, params);
        while (rs.next()) {
            Bill bill1 = new Bill();

            bill1.setId(rs.getInt("id"));
            bill1.setBillCode(rs.getString("billCode"));
            bill1.setProductName(rs.getString("productName"));
            bill1.setProductDesc(rs.getString("productDesc"));
            bill1.setProductUnit(rs.getString("productUnit"));
            bill1.setProductCount(rs.getBigDecimal("productCount"));
            bill1.setTotalPrice(rs.getBigDecimal("totalPrice"));
            bill1.setIsPayment(rs.getInt("isPayment"));
            bill1.setProviderId(rs.getInt("providerId"));
            bill1.setCreationDate(rs.getDate("creationDate"));

            billList.add(bill1);
        }
        BaseDao.closeResouce(null, pstm, rs);

        return billList;
    }

    @Override
    public int addBill(Connection connection, Bill bill) throws Exception {
        int addRows = 0;
        PreparedStatement pstm = null;

        if (connection != null) {
            String sql = "insert into smbms_bill(billCode, productName, productDesc, productUnit, productCount, totalPrice, " +
                    "isPayment, providerId, creationDate, createdBy) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {bill.getBillCode(), bill.getProductName(), bill.getProductDesc(),
                    bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(),
                    bill.getIsPayment(), bill.getProviderId(), bill.getCreationDate(), bill.getCreatedBy()
            };

            addRows = BaseDao.execute(connection, pstm, sql, params);
        }
        BaseDao.closeResouce(null, pstm, null);

        return addRows;
    }
}
