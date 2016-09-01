package cn.smbms.servlet.bill;

import cn.smbms.pojo.provider.Provider;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by alren on 16-9-1.
 */
@WebServlet(name = "FindProviderServlet")
public class FindProviderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.getProviderList(null);

        request.setAttribute("providerList", providerList);
        request.getRequestDispatcher("jsp/billAdd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
