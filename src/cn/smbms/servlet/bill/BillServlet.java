package cn.smbms.servlet.bill;

import cn.smbms.pojo.bill.Bill;
import cn.smbms.pojo.provider.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alren on 16-8-28.
 */
@WebServlet(name = "BillServlet")
public class BillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("Bid");
        String productName = request.getParameter("productName");
        String billCode = request.getParameter("billCode");

        Bill bill = new Bill();
        try {
            bill.setId(Integer.parseInt(id));
        } catch (Exception e) {}

        bill.setProductName(productName);
        bill.setBillCode(billCode);

        BillService billService = new BillServiceImpl();
        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.getProviderList(null);

        List<Bill> billList = billService.getBillList(bill);
        request.setAttribute("providerList", providerList);
        request.setAttribute("billList", billList);
        request.getRequestDispatcher("jsp/billList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
