package cn.smbms.service.user;

import cn.smbms.pojo.user.User;

import java.sql.Connection;
import java.util.List;

/**
 * Created by alren on 16-8-19.
 */
public interface UserService {
    public boolean add(User user);  //添加用户
    public User login(String userCode, String userPassword); //用户登陆时的验证
    public List<User> getUserList(String userName); //根据用户姓名的关键字查询
    public User selectUserCodeExist(String userCode); //判断用户是否存在
    public User findUser(int userId);  //通过id查找信息
    public boolean updateUser(User user); //更新数据
    public boolean deleteUser(int userId);//删除数据
    public boolean updatePwd(String newPwd, int userId); //修改密码
}
