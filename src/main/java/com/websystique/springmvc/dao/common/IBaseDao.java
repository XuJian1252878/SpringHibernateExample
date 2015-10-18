package com.websystique.springmvc.dao.common;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	//[public][static][final] 常量;
	//[public][abstract]方法;
	// 根据ID加载实体
	T get(Class<T> entityClazz , Serializable id);
	// 获取实体总数
	long findCount(Class<T> entityClazz);
	// 保存实体
	Serializable save(T entity);
	// 更新实体
	boolean update(T entity);
	// 删除实体
	boolean delete(T entity);
	// 根据id删除实体
	boolean delete(Class<T> entityClazz, Serializable id);
	// 获取所有实体
	List<T> findAll(Class<T> entityClazz);
}
