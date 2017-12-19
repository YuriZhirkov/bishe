package com.example.third;

import java.util.List;

/**
 * Created by zzg on 2017/9/12.
 */
public interface IAccountDAO {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
