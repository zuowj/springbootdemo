package cn.zuowenjun.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //指定为Spring Boot启动入口，内含多个spring所需要的注解
@MapperScan(basePackages="cn.zuowenjun.boot.mapper")//设置Mybaits扫描的mapper包路径
//@ComponentScan(basePackages= {"cn.zuowenjun.controller"}) //如果不在根包目录，则需指定spring管理的相关包路径
@EnableTransactionManagement //启动事务管理
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

}

