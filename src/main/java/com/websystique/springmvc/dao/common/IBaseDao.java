package com.websystique.springmvc.dao.common;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	//[public][static][final] ����;
	//[public][abstract]����;
	// ����ID����ʵ��
	T get(Class<T> entityClazz , Serializable id);
	// ��ȡʵ������
	long findCount(Class<T> entityClazz);
	// ����ʵ��
	Serializable save(T entity);
	// ����ʵ��
	boolean update(T entity);
	// ɾ��ʵ��
	boolean delete(T entity);
	// ����idɾ��ʵ��
	boolean delete(Class<T> entityClazz, Serializable id);
	// ��ȡ����ʵ��
	List<T> findAll(Class<T> entityClazz);
}
