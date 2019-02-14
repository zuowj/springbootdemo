package cn.zuowenjun.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zuowenjun.boot.EShopProperties;
import cn.zuowenjun.boot.SpringbootdemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootdemoApplication.class)
public class ConfigTests {
	
	@Autowired
	private EShopProperties shopProperties;
	
	@Test
	public void getConfigItems() {
		System.out.println(shopProperties.getAdminUserId() +":"+ shopProperties.getAdminUserPwd());
		System.out.println(shopProperties.getShopUserId() +":"+ shopProperties.getShopUserPwd());
		System.out.println(shopProperties.getShopName());
	}
}
