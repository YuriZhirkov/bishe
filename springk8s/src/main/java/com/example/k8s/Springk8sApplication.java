package com.example.k8s;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.k8s.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class Springk8sApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springk8sApplication.class, args);
	}
}
