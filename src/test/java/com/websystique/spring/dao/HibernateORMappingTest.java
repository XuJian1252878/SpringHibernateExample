package com.websystique.spring.dao;
// http://blog.arganzheng.me/posts/junit-and-spring-integration-ioc-autowire.html

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/*import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;*/
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.websystique.springmvc.model.Category;
import com.websystique.springmvc.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:/configs/spring-servlet.xml",
	"classpath:/configs/spring-hibernate.xml",
	"classpath:/configs/spring-beans.xml"
})
public class HibernateORMappingTest {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@BeforeClass  
    public static void beforeClass() {
/*		Resource resource = new ClassPathResource("/configs/spring-servlet.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		sessionFactory = (SessionFactory)beanFactory.getBean("sessionFactory");*/
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
		
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
	}

}
