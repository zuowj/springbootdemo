package cn.zuowenjun.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class SpringbootdemoAppConfigurer implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
		registration.addPathPatterns("/**");
		registration.excludePathPatterns("/*.html","/uploadimgs/*","/error","/api/login","/api/categorys","/api/goods*","/api/goods/*",
				"/hello/*","/test/*");
	}

}
