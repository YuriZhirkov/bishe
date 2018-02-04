package com.example.k8s.service.impl;

import com.example.k8s.mapper.UserMapper;
import com.example.k8s.model.User;
import com.example.k8s.service.UserService;
import com.example.k8s.untils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int create(User user) {
        if (StringUtils.isBlank(user.getFlagrole())){
            user.setFlagrole("user");
        }
        if (StringUtils.isBlank(user.getHeadurl())){
            user.setHeadurl("http://172.168.5.6/tuxiang.png");
        }
        Date date = new Date();
        user.setCreatetime(date.getTime());
        user.setUpdatetime(date.getTime());
        userMapper.insertSelective(user);
        return user.getId();
    }



    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageResult<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<User> pageResult = new PageResult<>();
        PageHelper.startPage(pageNum, pageSize);
        List<User> recordList  =  userMapper.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }

    @Override
    public void delete(Integer[] ids) {
        if (ids != null && ids.length > 0){
            for (int i=0 ; i < ids.length ; i++) {
                if (userMapper.selectByPrimaryKey(ids[i]) != null){
                    userMapper.deleteByPrimaryKey(ids[i]);
                }
            }
        }
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User login(String username, String password) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            Map<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            User user = userMapper.selectByUsernameAndPassword(map);
            if (user != null){
                return user;
            }
        }
        return null;
    }


    @Override
    public User selectByUsernmae(String username){
        return userMapper.selectByUsername(username);
    }
}
