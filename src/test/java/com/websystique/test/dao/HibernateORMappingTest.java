package com.websystique.test.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.websystique.springmvc.model.Category;
import com.websystique.springmvc.model.Product;

public class HibernateORMappingTest {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@BeforeClass  
    public static void beforeClass() {
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
		
		Session s = new AnnotationConfiguration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
	}

}
