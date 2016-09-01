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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alren on 16-8-24.
 */
@WebServlet(name = "ModifyServlet")
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId= request.getParameter("userId");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userType = request.getParameter("userType");

        User user = new User();
        user.setId(Integer.parseInt(userId));
        user.setUserName(userName);
        user.setGender(Integer.parseInt(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (Exception e){
            e.printStackTrace();
        }

        user.setPhone(phone);
        user.setAddress(address);
        user.setUserType(Integer.parseInt(userType));

        //设置修改者和修改时间
        user.setModifyDate(new Date());
        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        User modifyBy = (User)obj;
        user.setModifyBy(modifyBy.getId());

        UserService userService = new UserServiceImpl();

        if(userService.updateUser(user)) {
            request.getRequestDispatcher("/user.do").forward(request, response);//重定向到查询,避免页面刷新
        } else {
            request.getRequestDispatcher("jsp/userlist.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
