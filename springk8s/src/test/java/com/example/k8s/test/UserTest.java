package com.example.k8s.test;

import com.example.k8s.model.User;
import com.example.k8s.service.UserService;
import com.example.k8s.untils.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/24 0024.
 */
//@MapperScan("com.example.k8s.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void hello() {
        System.out.println("hello");
    }

    @Test
    public void createTest(){
        User user = new User();
        user.setUsername("122301022");
        user.setCnname("张志国");
        user.setAddress("湖北省监利县毛市镇");
        //Date或者String转化为时间戳
//        SimpleDateFormat format =  newSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time="1970-01-06 11:45:55";
//        Date date = format.parse(time);
//        System.out.print("Format To times:"+date.getTime());
        Date date = new Date();
        user.setCreatetime(date.getTime());
        user.setEmail("1562033492@qq.com");
        user.setFlagrole("user");
        user.setHeadurl("http:175.23.66.99/index/123.jpg");
        user.setLastlogintime(date.getTime());
        user.setMobile("15818735390");
        user.setPassword("125869");
        user.setState("login");
        user.setUpdatetime(date.getTime());
        int ret = userService.create(user);
        System.out.println("zzg + "+ret);
    }

    @Test
    public void deleteTest(){
        Integer[] ids = {1};
        userService.delete(ids);
        System.out.println("zzg-------------------------------");
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setId(12234444);
        user.setUsername("122301022");
        user.setCnname("张志国");
        user.setAddress("湖北省监利县毛市镇");
        //Date或者String转化为时间戳
//        SimpleDateFormat format =  newSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time="1970-01-06 11:45:55";
//        Date date = format.parse(time);
//        System.out.print("Format To times:"+date.getTime());
        Date date = new Date();
        user.setCreatetime(date.getTime());
        user.setEmail("1562033492@qq.com");
        user.setFlagrole("user");
        user.setHeadurl("http:175.23.66.99/index/123.jpg");
        user.setLastlogintime(date.getTime());
        user.setMobile("15818735390");
        user.setPassword("125869");
        user.setState("login");
        user.setUpdatetime(date.getTime());
        userService.update(user);
        System.out.println("zzg-------------------------------");
    }

    @Test
    public void getTest(){
        User user = userService.selectByPrimaryKey(1224344);
        System.out.println("zzg-------------------------------"+user.toString());
    }

    @Test
    public void listTest(){
        PageResult<User> pageResult = userService.findAllUser(1,2);
        System.out.println("zzg-------------------------------"+pageResult.toString());
    }
}
