package cn.smbms.servlet.user;

import cn.smbms.pojo.user.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
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

/**
 * Created by alren on 16-8-22.
 */
@WebServlet(name = "isExistServlet")
public class isExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //判断用户账号是否可用
        String userCode = request.getParameter("userCode");

        HashMap<String, String> resultMap = new HashMap<>();

        if (StringUtils.isNullOrEmpty(userCode)) {
            resultMap.put("userCode", "empty");
        } else {
            UserService userService = new UserServiceImpl();
            User user = userService.selectUserCodeExist(userCode);

            if (user == null) {
                resultMap.put("userCode", "notexist");
            } else {
                resultMap.put("userCode", "exist");
            }
        }

        //将resultMap中的值，转换为json串输出
        //配置上下文的输出类型
        response.setContentType("application/json");
        //从response对象中获取往外输出的writer对象
        PrintWriter outPrintWriter = response.getWriter();
        //把resultMap转为json字符串输出
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush(); //刷新
        outPrintWriter.close(); //关闭
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
