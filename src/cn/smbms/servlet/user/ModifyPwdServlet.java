package cn.smbms.servlet.user;

import cn.smbms.pojo.user.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
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
@WebServlet(name = "ModifyPwdServlet")
public class ModifyPwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPwd = request.getParameter("newPwd");
        UserService userService = null;
        //获取Session中的user，从中间取出user
       Object obj = request.getSession().getAttribute(Constants.USER_SESSION);

        //存入返回的信息,然后转换为json对象,进行返回
        Map<String, String> mapPwd = new HashMap<>();
        if (!StringUtils.isNullOrEmpty(newPwd) && obj != null) {
            User user = (User)obj;
            userService  = new UserServiceImpl();
            if (userService.updatePwd(newPwd, user.getId())) {
                //更新成功，更新当前的session中的密码信息
                user.setUserPassword(newPwd);
                request.getSession().setAttribute(Constants.USER_SESSION, user);
                //设置提示信息
                mapPwd.put("message", "ok");
            } else {
                mapPwd.put("message", "failed");
            }
        } else {
            mapPwd.put("message", "failed");
        }

        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONArray.toJSONString(mapPwd));
        printWriter.flush();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
