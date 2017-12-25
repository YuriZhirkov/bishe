package com.example.k8s.test;

import com.example.k8s.model.User;
import com.example.k8s.service.UserService;
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
    public void addTest(){
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
        int ret = userService.addUser(user);
        System.out.println("zzg + "+ret);
    }
}
