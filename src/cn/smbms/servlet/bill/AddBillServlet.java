package cn.smbms.servlet.bill;

import cn.smbms.pojo.bill.Bill;
import cn.smbms.pojo.provider.Provider;
import cn.smbms.pojo.user.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alren on 16-8-31.
 */
@WebServlet(name = "AddBillServlet")
public class AddBillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billCode = request.getParameter("billCode");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String payed = request.getParameter("payed");

        Bill bill = new Bill();
        //设置规则，小数点后保留两位
        BigDecimal bigDecimal;
        BigDecimal bigDecimal1;

        bigDecimal = new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN);
        bigDecimal1 = new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN);
        bill.setProductCount(bigDecimal);
        bill.setTotalPrice(bigDecimal1);

        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProviderId(Integer.parseInt(providerId));

        bill.setIsPayment(payed.equals("1") ? 1 : 0);
        bill.setCreationDate(new Date());
        User user = (User)(request.getSession().getAttribute(Constants.USER_SESSION));
        bill.setCreatedBy(user.getId());


        BillService billService = new BillServiceImpl();
        if (billService.addBill(bill)) {
            request.getRequestDispatcher("/bill.do?productName=&billCode=").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/billList.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
