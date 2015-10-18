package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.common.IBaseService;

public interface IUserService extends IBaseService<User> {
	public int getUserCount();
	
	public List<User> getAllUser();
	
	public boolean createUser(User user);
	
	public User findUserById(int id);
	
	public boolean delUserById(int id);
	
	public boolean saveUser(User user);
}
