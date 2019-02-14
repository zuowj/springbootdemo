package cn.zuowenjun.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zuowenjun.boot.domain.*;
import cn.zuowenjun.boot.service.*;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private ShopUserService shopUserService;
	
	@GetMapping("/userlist")
	public String list(Model model) {
		
		List<ShopUser> users= shopUserService.getAll();
		model.addAttribute("title", "测试使用thymeleaf模板引擎展示数据");
		model.addAttribute("users", users);
		
		//可以在application.properties添加如下配置，以改变thymeleaf的默认设置
		//spring.thymeleaf.prefix="classpath:/templates/" 模板查找路径
		//spring.thymeleaf.suffix=".html" 模板后缀名
		
		return "/test";//默认自动查找路径：src/main/resources/templates/*.html
	}
}
