package com.websystique.springmvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping(value="/count", method=RequestMethod.GET)
	public ModelAndView userCount(){
		int count = userService.getUserCount();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userCount", count);
		mv.setViewName("user/userCount");
		return mv;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView getUserList(){
		List<User> users = userService.getAllUser();
		ModelAndView mv = new ModelAndView();
		System.out.println("log========table 'user' all records: " + users.size());
		mv.addObject("userList", users);
		mv.setViewName("user/List");
		return mv;
	}
	
	// ��ת������û��Ľ��档
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView getAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/add");
		return mv;
	}
	
	// ��д�û���Ϣ�������ϴ��û���Ϣ��@ModelAttribute �Ǳ��ύ������������
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("user") User user){
		userService.createUser(user);
		// ҳ����ת
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/show/{userid}",method=RequestMethod.GET)
	public ModelAndView show(@PathVariable int userid){
		User user = userService.findUserById(userid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("user/detail");
		return mv;
	}
	
	@RequestMapping(value="/del/{userid}",method=RequestMethod.GET)
	public String del(@PathVariable int userid){
		userService.delUserById(userid);
		// ҳ����ת
		return "redirect:/user/list";
	}
	
	// 对用户的数据信息进行编辑。
	@RequestMapping(value="/edit/{userid}",method=RequestMethod.GET)
	public ModelAndView getEdit(@PathVariable int userid, Model model){
		User user = userService.findUserById(userid);
		// ��model�����������ݡ�
		model.addAttribute("userAttribute", user);
		// ָ����Щ����������һ��jspҳ���Ͻ�����ʾ��
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/edit");
		return mv;
	}
	
	// ���û������ݸ���֮���ϴ������ݿ��С�
	@RequestMapping(value="/save/{userid}",method=RequestMethod.POST)
	public String saveEdit(@ModelAttribute("userAttribute") User user, @PathVariable int userid){
		userService.saveUser(user);
		return "redirect:/user/list";
	}
}
