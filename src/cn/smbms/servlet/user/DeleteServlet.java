package cn.smbms.servlet.user;

import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by alren on 16-8-23.
 */
@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        int deleteId;

        try {
            deleteId = Integer.parseInt(userid);
        } catch (Exception e) {
            deleteId = 0;
        }

        HashMap<String, String> usersMap = new HashMap<>();
        if (deleteId <= 0) {
            usersMap.put("result", "notexits");
        } else {
            UserService userService = new UserServiceImpl();
            if (userService.deleteUser(deleteId)) {
                usersMap.put("result", "success");
            } else {
                usersMap.put("result", "failed");
            }
        }

        //设置上下文的输出环境为json
        response.setContentType("application/json");
        //从response中获取输出对象
        PrintWriter printWriter = response.getWriter();
        //把userMap转化为json串输出
        printWriter.write(JSONArray.toJSONString(usersMap));
        printWriter.flush(); //刷新
        printWriter.close(); //关闭
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
