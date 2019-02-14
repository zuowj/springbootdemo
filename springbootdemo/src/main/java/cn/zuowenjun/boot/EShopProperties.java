package cn.zuowenjun.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EShopProperties {
	
	@Value(value="${eshop.admin.user.id}")
	private String adminUserId; 
	
	@Value(value="${eshop.admin.user.pwd}")
	private String adminUserPwd;
	
	@Value("${eshop.user.id}")
	private String shopUserId;
	
	@Value("${eshop.user.pwd}")
	private String shopUserPwd;
	
	@Value(value="${eshop.name}")
	private String shopName;
	
	public String getAdminUserId() {
		return this.adminUserId;
	}
	
	public String setAdminUserId(String adminUserId) {
		return this.adminUserId=adminUserId;
	}
	
	public String getAdminUserPwd() {
		return this.adminUserPwd;
	}
	
	public String setAdminUserPwd(String adminUserPwd) {
		return this.adminUserPwd=adminUserPwd;
	}
	
	public String getShopUserId() {
		return this.shopUserId;
	}
	
	public String setShopUserId(String shopUserId) {
		return this.shopUserId=shopUserId;
	}
	
	public String getShopUserPwd() {
		return this.shopUserPwd;
	}
	
	public String setShopUserPwd(String shopUserPwd) {
		return this.shopUserPwd=shopUserPwd;
	}
	
	public String getShopName() {
		return this.shopName;
	}
	
	public String setShopName(String shopName) {
		return this.shopName=shopName;
	}
	
}
