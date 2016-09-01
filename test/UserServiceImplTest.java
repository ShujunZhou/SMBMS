
import cn.smbms.pojo.user.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
//import junit.extensions.TestSetup;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 19, 2016</pre> 
* @version 1.0 
*/

public class UserServiceImplTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
    }

    @Test
    public void testgetUserList() throws Exception {
        List<User> userList = userService.getUserList("");

        Iterator it = userList.iterator();
        while (it.hasNext()) {
            User user = (User)it.next();
            System.out.println(user.getUserName());
        }
    }
    @Test
    public void testAdd() throws Exception {
        User user = new User();

        user.setUserCode("2567");
        user.setUserName("test");
        user.setUserPassword("12346");
        user.setGender(1);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1995-12-19"));
        user.setPhone("15236987452");
        user.setAddress("西安长安区");
        user.setUserType(3);
        user.setCreateBy(7);
        user.setCreationDate(new Date());
        boolean addRows = userService.add(user);

        Assert.assertEquals(addRows, true);
    }

    @Test
    public void testFindUser() throws Exception {
        User user = userService.findUser(1);
        Assert.assertTrue(user.getUserName().equals("jack"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        boolean flag = userService.deleteUser(10);
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateUser() throws Exception {

        User user = new User();
        user.setId(7);
        user.setUserName("update");
        user.setGender(1);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1997-2-10"));
        user.setPhone("15236987444");
        user.setAddress("城都长安区");
        user.setUserType(3);
        user.setModifyBy(7);
        user.setModifyDate(new Date());

        boolean flag = userService.updateUser(user);
        Assert.assertTrue(flag);
        testgetUserList();
    }

    @Test
    public void testUpdatePwd() throws Exception {
        String newPwd = "654321";

        Boolean flag = userService.updatePwd(newPwd, 10);
        Assert.assertTrue(flag);
    }
} 
