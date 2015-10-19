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

	//�����е�DAO��ʵ�ֻ����Ĳ����ӿ�IBaseDao
    //����ʵ��IBaseDao�еĻ�������֮�⣬�ض���DAOҪʵ���������������ڶ�Ӧ�Ľӿ�DAO�ж��巽����
	
	/**
	 * ������@Resource��ǩ��ʵ�֣�����Ҫʵ�ֶ�Ӧ��getset������
	@Resource(name="baseDao")
	private BaseDao<User> baseDao;
	*/
	
/**
 * ʹ��Autowiredʵ�֣����븽����get��set�ķ�����
 * 	@Autowired
	private BaseDao<User> baseDao;
	
	public BaseDao<User> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<User> baseDao) {
		this.baseDao = baseDao;
	}*/

	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return get(User.class, id);
	}

	public long getUserCount() {
		// TODO Auto-generated method stub
		return findCount(User.class);
	}

}
