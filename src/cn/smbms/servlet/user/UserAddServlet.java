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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alren on 16-8-22.
 */
@WebServlet(name = "UserAddServlet")
public class UserAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userType = request.getParameter("userType");

        User user = new User();
        user.setUserName(userName);
        user.setUserCode(userCode);
        user.setUserPassword(userPassword);
        user.setGender(Integer.parseInt(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserType(Integer.parseInt(userType));

        //设置创建者和创建时间
        user.setCreationDate(new Date());
        Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
        User createBy = (User)obj;
        user.setCreateBy(createBy.getId());

        UserService userService = new UserServiceImpl();
        if (userService.add(user)) {//新增完，需要重新查找，在页面更新
            request.getRequestDispatcher("/user.do").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/adduser.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
