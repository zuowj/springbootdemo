package cn.zuowenjun.boot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zuowenjun.boot.domain.ApiResultMsg;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {// 未登录则转到登录页面
			boolean isAjaxRequest = false;
			boolean isAcceptJSON = false;
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				isAjaxRequest = true;
			}

			if (request.getHeader("Accept") != null && request.getHeader("Accept").contains("application/json")) {
				isAcceptJSON = true;
			}

			if(isAjaxRequest || isAcceptJSON) {
				
				//使用jackson序列化JSON
				ApiResultMsg msg=new ApiResultMsg(-1,"未登录，禁止访问",null);
				ObjectMapper mapper = new ObjectMapper();
				String msgJson= mapper.writeValueAsString(msg);
				
				responseJson(response,msgJson);
				
			}else {
				
				response.sendRedirect("/login.html");
			}
			return false;
		}

		return true;
	}

	private void responseJson(HttpServletResponse response, String json) throws Exception {
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("applcation/json; charset=utf-8");
		try {
			writer = response.getWriter();
			writer.print(json);

		} catch (IOException e) {
			logger.error("response error", e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
