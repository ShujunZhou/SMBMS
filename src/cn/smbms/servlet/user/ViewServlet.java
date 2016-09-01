package cn.smbms.servlet.user;

import cn.smbms.pojo.user.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alren on 16-8-23.
 */
@WebServlet(name = "ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        UserService userService;

        if (!StringUtils.isNullOrEmpty(userid)) {
            userService = new UserServiceImpl();
            User user = userService.findUser(Integer.parseInt(userid));

            request.setAttribute("user", user);
            request.getRequestDispatcher("jsp/userView.jsp").forward(request, response);
        } else {
            request.setAttribute("user", "falied");
            request.getRequestDispatcher("jsp/userlist.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
