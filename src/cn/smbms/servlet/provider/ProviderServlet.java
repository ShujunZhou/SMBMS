package cn.smbms.servlet.provider;

import cn.smbms.pojo.provider.Provider;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alren on 16-8-25.
 */
@WebServlet(name = "ProviderServlet")
public class ProviderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryProvider = request.getParameter("queryProvider"); //获取此值用于判断以什么条件查找.
        String queryProviders = request.getParameter("queryProviders"); //获取此值用于查询。
        String queryId = request.getParameter("queryId"); //此元素用于达到在不同的需求下，同一个查找可以跳转至不同的页面，达到代码复用
        Provider provider;
        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList;

        if (StringUtils.isNullOrEmpty(queryProvider)) {//当设定属性为空时，默认查询全部
            provider = null;
            providerList = providerService.getProviderList(provider);
            request.setAttribute("providerList", providerList);
            request.getRequestDispatcher("jsp/providerList.jsp").forward(request, response);
        } else if (queryProvider.equals("proName")) {//根据供应商名称查询
            provider = new Provider();
            provider.setProName(queryProviders);
            providerList = providerService.getProviderList(provider);
            request.setAttribute("providerList", providerList);
            request.getRequestDispatcher("jsp/providerList.jsp").forward(request, response);
        } else if (queryProvider.equals("proCode")) { //根据供应商编码查询
            provider = new Provider();
            Map<String, String> map = new HashMap<>();
            provider.setProCode(queryProviders);
            providerList = providerService.getProviderList(provider);
            if (providerList.size() > 0) {
                map.put(Constants.SYS_MESSAGE, "exist");
            } else {
                map.put(Constants.SYS_MESSAGE, "empty");
            }

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSONArray.toJSONString(map));
            printWriter.flush();
            printWriter.close();

        } else if (queryProvider.equals("id")) {//根据id查询
            provider = new Provider();
            provider.setId(Integer.parseInt(queryProviders));
            providerList = providerService.getProviderList(provider);
            request.setAttribute("providerList", providerList);
            if (queryId == null) {
                request.getRequestDispatcher("jsp/providerView.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("jsp/providerModify.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
