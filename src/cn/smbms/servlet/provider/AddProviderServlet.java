package cn.smbms.servlet.provider;

import cn.smbms.pojo.provider.Provider;
import cn.smbms.pojo.user.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by alren on 16-8-28.
 */
@WebServlet(name = "AddProviderServlet")
public class AddProviderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Provider provider = new Provider();
        String proCode = request.getParameter("proCode");
        String proName = request.getParameter("proName");
        String proDesc = request.getParameter("proDesc");
        String proContact = request.getParameter("proContact");
        String proPhone = request.getParameter("proPhone");
        String proAddress = request.getParameter("proAddress");
        String proFax = request.getParameter("proFax");

        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        User user = (User) obj;

        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProFax(proFax);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setCreatedBy(user.getId());
        provider.setCreationDate(new Date());

        ProviderService providerService = new ProviderServiceImpl();
        if (providerService.addProvider(provider)) {
            request.getRequestDispatcher("/provider.do").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/providerList.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
