package com.websystique.springmvc.dao.common;

/**
 * @Repository @Service @Controller @CoMponent 这些标签实际上就是定义了web中bean的类型
 * 这跟spring-beans.xml文件中bean的配置的作用是一样的，二者可以选择其一。
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
	 * @Resource 标签相当于自动帮你实现了默认的get set方法，你自身不需要定义它们。
	 * @Resource 自动装配 相当于get() set()
	 */
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;//整个程序中只有一个sessionFactory。
	
	/**
	 * 在使用@Autowired标签的时候，必须同时实现需要注入元素的get set方法。
	 * @Autowired
	 * // 依赖注入SessionFactory所需的setter方法
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
	
	// 根据ID加载实体
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz , Serializable id)
	{
		return (T)sessionFactory.getCurrentSession()
			.get(entityClazz , id);
	}
	
	// 根据带占位符参数HQL语句查询实体
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql , Object... params)
	{
		// 创建查询
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hql);
		// 为包含占位符的HQL语句设置参数
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}
	
	// 获取实体总数
	public long findCount(Class<T> entityClazz)
	{
		List<T> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		// 查询得到的实体总数
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}
	
	// 保存一个新的实例对象
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
	
	// 跟据hql语句查询实体。
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql){
		return (List<T>)sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
