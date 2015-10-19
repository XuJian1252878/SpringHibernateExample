package com.websystique.springmvc.dao;

import com.websystique.springmvc.dao.common.IBaseDao;
import com.websystique.springmvc.model.User;

public interface IUserDao extends IBaseDao<User> {
	public User getUserById(Integer id);
	public long getUserCount();
}
