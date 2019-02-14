package cn.zuowenjun.boot.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.zuowenjun.boot.EShopProperties;
import cn.zuowenjun.boot.domain.ShopUser;
import cn.zuowenjun.boot.mapper.ShopUserMapper;
import cn.zuowenjun.boot.service.ShopUserService;

@Service
public class ShopUserServiceImpl implements ShopUserService {

	private ShopUserMapper shopUserMapper;
	
	private EShopProperties shopProperties;
	
	@Autowired
	public ShopUserServiceImpl(ShopUserMapper shopUserMapper,EShopProperties shopProperties) {
		this.shopUserMapper=shopUserMapper;
		this.shopProperties=shopProperties;
	}

	@Override
	public List<ShopUser> getAll() {
		return shopUserMapper.selectAll();
	}

	@Override
	public ShopUser get(String userId) {
		return shopUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public String getCurrentLoginUser() {
		if(getRequest().getSession().getAttribute("loginUser")==null) {
			return null;
		}
		
		return getRequest().getSession().getAttribute("loginUser").toString();
		
	}
	

	@Override
	public String login(String uid, String pwd) {
		if(shopProperties.getShopUserId().equalsIgnoreCase(uid) && 
				shopProperties.getShopUserPwd().equals(pwd)) {
			getRequest().getSession().setAttribute("loginUser", uid);
			return null;
		}else {
			return "用户名或密码不正确！";
		}
	}

	@Override
	public void logout() {
		getRequest().getSession().removeAttribute("loginUser");
	}
	
	
	private HttpServletRequest getRequest() {
        HttpServletRequest  request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


	
	
}
