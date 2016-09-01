package cn.smbms.servlet.user;

import cn.smbms.pojo.user.User;
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
import java.util.Map;

/**
 * Created by alren on 16-8-24.
 */
@WebServlet(name = "UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPwd = request.getParameter("oldPwd");
        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        User user = (User)obj;

        Map<String, String> result = new HashMap<>();
        if (StringUtils.isNullOrEmpty(oldPwd)) {
            result.put("result", "empty");
        } else {
            if (oldPwd.equals(user.getUserPassword())) {
                result.put("result", "ok");
            } else {
                result.put("result", "failed");
            }
        }

        //将result转换成JSON对象
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONArray.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
