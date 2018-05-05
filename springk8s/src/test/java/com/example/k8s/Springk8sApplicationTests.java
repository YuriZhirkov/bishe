package com.example.k8s;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springk8sApplicationTests {

    @Test
    public void testk8s() {
        Date date = new Date();

        StringBuffer orderCode = new StringBuffer("");
        orderCode.append(1900 + date.getYear()).append(date.getMonth() + 1).append(date.getDay() - 1);
        System.out.println(orderCode.toString());
        System.out.println("时间" + (1900 + date.getYear()) + "年" + (date.getMonth() + 1) + "月" + (date.getDay() - 1) + "日");
    }

    @Test
    public void date2Str() {

        SimpleDateFormat df = null;
        String returnValue = "";


        df = new SimpleDateFormat("yyyyMMdd");
        returnValue = df.format(new Date());

        System.out.println(returnValue);
        StringBuffer stringBuffer = new StringBuffer("");
        int num=(int)(1+Math.random()*100);
        if (num<10){
            stringBuffer.append("00").append(num);
        }
        if (num>=10 && num<100){
            stringBuffer.append("0").append(num);
        }
        if (num==100){
            stringBuffer.append(num);
        }
        System.out.println(stringBuffer.toString());
    }

}
