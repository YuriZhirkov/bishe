package com.example.k8s.service;
import com.example.k8s.model.User;
import java.util.List;
/**
 * Created by Administrator on 2017/12/24 0024.
 */
public interface UserService {
    int addUser(User user);
    List<User> findAllUser(int pageNum, int pageSize);
}
