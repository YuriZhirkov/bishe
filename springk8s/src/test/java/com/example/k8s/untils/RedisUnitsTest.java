package com.example.k8s.untils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zzg on 2018/3/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUnitsTest {

    @Autowired
    private RedisUnits redisUnits;
    @Test
    public void setValue() throws Exception {
        redisUnits.setValue("aaa","zzg");
        System.out.println("结果为："+redisUnits.getValue("aaa"));
    }

    @Test
    public void setValue1() throws Exception {

    }

    @Test
    public void getValue() throws Exception {
    }

    @Test
    public void multiSet() throws Exception {
    }

    @Test
    public void multiGet() throws Exception {
    }

}