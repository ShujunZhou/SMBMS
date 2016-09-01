package cn.smbms.servlet.provider;

import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alren on 16-8-27.
 */
@WebServlet(name = "DeleteProviderServlet")
public class DeleteProviderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String providerId = request.getParameter("providerId");
        ProviderService providerService = new ProviderServiceImpl();
        Map<String, String> providerMap = new HashMap<String, String>();

        if (providerService.deleteProvider(Integer.parseInt(providerId))) {
            providerMap.put(Constants.SYS_MESSAGE, "succeed");
//            request.getRequestDispatcher("/provider.do").forward(request, response);  error
        } else {
            providerMap.put(Constants.SYS_MESSAGE, "failed");

        }

        //将结果集转换为json对象返回到前台
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONArray.toJSONString(providerMap));
        printWriter.flush();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
