package cn.zuowenjun.boot.controller;

import org.springframework.web.bind.annotation.*;

import cn.zuowenjun.boot.domain.*;

@RestController
public class DemoController {

	@RequestMapping(value="/hello/json",produces="application/json;charset=utf-8")
	public HelloDto hellojson()
	{
		HelloDto dto=new HelloDto();
		dto.setMessage("hello,zuowenjun.cn,hello java spring boot!");
		
		return dto;
	}
	
	@RequestMapping(value="/hello/xml",produces="text/xml;charset=utf-8")
	public HelloDto helloxml()
	{
		HelloDto dto=new HelloDto();
		dto.setMessage("hello,zuowenjun.cn,hello java spring boot!");
		
		return dto;
	}
}
