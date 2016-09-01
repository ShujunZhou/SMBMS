package cn.smbms.servlet.user;

import cn.smbms.pojo.user.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import cn.smbms.tools.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alren on 16-8-19.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        UserService userService = new UserServiceImpl();

        //使用service方法，进行用户匹配
        User user = userService.login(userCode, userPassword);
        if (null != user) { //登陆成功
            //放入session
            request.getSession().setAttribute(Constants.USER_SESSION, user);
            //页面跳转frame.jsp
            response.sendRedirect("jsp/frame.jsp");
        } else {
            //页面跳转login.jsp,带出提示提示信息--转发
            request.setAttribute("error", "用户名或密码不正确");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
