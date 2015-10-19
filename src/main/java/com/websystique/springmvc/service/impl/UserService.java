package com.websystique.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.IUserDao;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.IUserService;
import com.websystique.springmvc.service.common.BaseService;

@Service("userService")
@Transactional
public class UserService extends BaseService<User> implements IUserService {
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public int getUserCount(){
		return (int)userDao.getUserCount();
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAll(User.class);
	}

	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return true;
	}

	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(new Integer(id));
	}

	public boolean delUserById(int id) {
		// TODO Auto-generated method stub
		userDao.delete(userDao.getUserById(new Integer(id)));
		return true;
	}

	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		return true;
	}
}
