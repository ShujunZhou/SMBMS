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
 * Created by alren on 16-8-26.
 */
@WebServlet(name = "ModifyProviderServlet")
public class ModifyProviderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //修改信息不能修改id,但是需要id去修改信息
        String proId = request.getParameter("proId");
        String proName = request.getParameter("proName");
        String proDesc = request.getParameter("proDesc");
        String proContact = request.getParameter("proContact");
        String proPhone = request.getParameter("proPhone");
        String proFax = request.getParameter("proFax");
        String proAddress = request.getParameter("proAddress");

        Provider provider = new Provider();
        provider.setId(Integer.parseInt(proId));
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);

        //从session中获取当前修改者的信息，进行记录
        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        User user = (User)obj;
        provider.setModifyBy(user.getId());
        provider.setModifyDate(new Date());

        ProviderService providerService = new ProviderServiceImpl();
        if (providerService.modifyProvider(provider)) {//修改成功后，再次跳转进行查询，使页面数据保持最新
            request.getRequestDispatcher("/provider.do").forward(request, response);
        } else { //修改失败，跳转至上一页面
            request.getRequestDispatcher("jsp/providerList.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
