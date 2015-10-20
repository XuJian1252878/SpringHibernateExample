package com.websystique.test.dao;

import java.io.File;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.websystique.springmvc.model.Category;
import com.websystique.springmvc.model.Product;

public class HibernateORMappingTest {
	
	private static SessionFactory sessionFactory;

	@BeforeClass  
    public static void beforeClass() {
//		String classpath = HibernateORMappingTest.class.getClassLoader().getResource("").getPath();
//		String path = classpath+File.separator+"configs"+File.separator+"spring-hibernate.xml";
//		String path = HibernateORMappingTest.class.getResource("/configs/spring-hibernate.xml").getPath();
		Resource resource = new ClassPathResource("/configs/spring-hibernate.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		sessionFactory = (SessionFactory)beanFactory.getBean("sessionFactory");
    }  
	
	@AfterClass  
    public static void afterClass() {
    } 
	
	@Test
	public void testSaveCategory(){
		System.out.println("test save category");
		Product p1 = new Product();
		p1.setName("p1");
		Product p2 = new Product();
		p2.setName("p2");
		
		Category c = new Category();
		c.getProducts().add(p1);
		c.getProducts().add(p2);
		
		p1.setCategory(c);
		p2.setCategory(c);
		

	    Assert.assertNotNull("the Session Factory is null", sessionFactory);

		
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
	}

}
