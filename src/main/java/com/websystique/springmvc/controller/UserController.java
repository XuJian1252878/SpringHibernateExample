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
	
	// 跳转到添加用户的界面。
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView getAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/add");
		return mv;
	}
	
	// 填写用户信息，并且上传用户信息。@ModelAttribute 是表单提交上来的数据吗？
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("user") User user){
		userService.createUser(user);
		// 页面跳转
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
		// 页面跳转
		return "redirect:/user/list";
	}
	
	// 对用户的信息进行更新编辑。
	@RequestMapping(value="/edit/{userid}",method=RequestMethod.GET)
	public ModelAndView getEdit(@PathVariable int userid, Model model){
		User user = userService.findUserById(userid);
		// 向model表格中添加数据。
		model.addAttribute("userAttribute", user);
		// 指明这些内容是在哪一个jsp页面上进行显示。
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/edit");
		return mv;
	}
	
	// 将用户的数据更新之后上传到数据库中。
	@RequestMapping(value="/save/{userid}",method=RequestMethod.POST)
	public String saveEdit(@ModelAttribute("userAttribute") User user, @PathVariable int userid){
		userService.saveUser(user);
		return "redirect:/user/list";
	}
}
