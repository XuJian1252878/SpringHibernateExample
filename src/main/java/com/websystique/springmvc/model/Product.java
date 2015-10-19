package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="T_PRODUCT")
@Proxy(lazy=true)
public class Product {
	
	protected Product(){
		
	}
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="increment")
	@Column(name="id", length=32)
	private Integer id;
	
	@Column(name="categoryId", length=32)
	private Integer categoryId;
}
