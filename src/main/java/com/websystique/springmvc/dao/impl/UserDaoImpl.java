package com.websystique.springmvc.dao.impl;

import org.springframework.stereotype.Repository;

//import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;

//import com.websystique.springmvc.dao.BaseDao;
import com.websystique.springmvc.dao.IUserDao;
import com.websystique.springmvc.dao.common.BaseDaoImpl;
import com.websystique.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	//让所有的DAO都实现基本的操作接口IBaseDao
    //除了实现IBaseDao中的基本操作之外，特定的DAO要实现其他操作可以在对应的接口DAO中定义方法，
	
	/**
	 * 而对于@Resource标签的实现，不需要实现对应的getset方法。
	@Resource(name="baseDao")
	private BaseDao<User> baseDao;
	*/
	
/**
 * 使用Autowired实现，必须附加上get和set的方法。
 * 	@Autowired
	private BaseDao<User> baseDao;
	
	public BaseDao<User> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<User> baseDao) {
		this.baseDao = baseDao;
	}*/

	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return get(User.class, id);
	}

	public long getUserCount() {
		// TODO Auto-generated method stub
		return findCount(User.class);
	}

}
