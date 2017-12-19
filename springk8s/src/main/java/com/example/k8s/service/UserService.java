package com.example.k8s.service;

import com.example.k8s.model.User;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */
public interface  UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
