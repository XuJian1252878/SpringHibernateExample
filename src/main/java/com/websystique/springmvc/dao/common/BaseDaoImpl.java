package com.websystique.springmvc.dao.common;

/**
 * @Repository @Service @Controller @CoMponent ��Щ��ǩʵ���Ͼ��Ƕ�����web��bean������
 * ���spring-beans.xml�ļ���bean�����õ�������һ���ģ����߿���ѡ����һ��
 */

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("baseDao")
@Transactional
public class BaseDaoImpl<T> implements IBaseDao<T> {
	
	/**
	 * @Resource ��ǩ�൱���Զ�����ʵ����Ĭ�ϵ�get set��������������Ҫ�������ǡ�
	 * @Resource �Զ�װ�� �൱��get() set()
	 */
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;//����������ֻ��һ��sessionFactory��
	
	/**
	 * ��ʹ��@Autowired��ǩ��ʱ�򣬱���ͬʱʵ����Ҫע��Ԫ�ص�get set������
	 * @Autowired
	 * // ����ע��SessionFactory�����setter����
		public void setSessionFactory(SessionFactory sessionFactory)
		{
			this.sessionFactory = sessionFactory;
		}
		public SessionFactory getSessionFactory()
		{
			return this.sessionFactory;
		}
	 *
	 */
	
	// ����ID����ʵ��
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz , Serializable id)
	{
		return (T)sessionFactory.getCurrentSession()
			.get(entityClazz , id);
	}
	
	// ���ݴ�ռλ������HQL����ѯʵ��
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql , Object... params)
	{
		// ������ѯ
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hql);
		// Ϊ����ռλ����HQL������ò���
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}
	
	// ��ȡʵ������
	public long findCount(Class<T> entityClazz)
	{
		List<T> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		// ��ѯ�õ���ʵ������
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}
	
	// ����һ���µ�ʵ������
	public Serializable save(T entity) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().save(entity);
	}
	
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return true;
	}
	
	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(entity);
		return true;
	}
	
	public boolean delete(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		return delete(get(entityClazz, id));
	}
	
	public List<T> findAll(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		return find("from " + entityClazz.getName());
	}
	
	// ����hql����ѯʵ�塣
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql){
		return (List<T>)sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
