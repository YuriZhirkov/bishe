package com.example.k8s.service;
import com.example.k8s.model.User;
import com.example.k8s.untils.PageResult;

import java.util.List;
/**
 * Created by Administrator on 2017/12/24 0024.
 */
public interface UserService {
    int create(User user);

    User selectByUsernmae(String username);

    PageResult<User> findAllUser(int pageNum, int pageSize);

    void delete(Integer[] ids);

    void update(User user);

    User selectByPrimaryKey(Integer id);

    User login(String username,String password);


}
