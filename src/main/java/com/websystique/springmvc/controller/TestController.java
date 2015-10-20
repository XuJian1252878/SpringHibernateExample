package com.websystique.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

@RequestMapping("/test")
public class TestController extends AbstractController implements LastModified{

	private long lastModified;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		res.getWriter().write("hello world!!");
		
		return null;
	}

	public long getLastModified(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		// 此处更新的条件：如果内容有更新，应该重新返回内容最新修改的时间戳
		if(lastModified == 0L){
			lastModified = System.currentTimeMillis();
		}
		return lastModified;
	}

}
