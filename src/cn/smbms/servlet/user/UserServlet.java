package cn.smbms.servlet.user;

import cn.smbms.pojo.user.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alren on 16-8-20.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryname = request.getParameter("queryname");
        UserService userService = new UserServiceImpl();

        //当用户什么都不输入时，%null%，所以让userName="",默认查询 全部
        if (queryname == null) {
            queryname = "";
        }
        List<User> userList = userService.getUserList(queryname);
        request.setAttribute("userList", userList);

        //将queryname放入request的作用域，在页面显示
        request.setAttribute("queryname", queryname);
        request.getRequestDispatcher("jsp/userlist.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
