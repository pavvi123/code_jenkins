package com.example.demo.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.demo.config.AttributeEncryptor;
import com.example.demo.config.IntergerConverter;

@Entity
public class Employee {

    @Id
    @GeneratedValue
   
    private long id;
    @Convert(converter = AttributeEncryptor.class)
    private String name;
    private String email;
    
    @Convert(converter = IntergerConverter.class)
    private long num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}
    
    
}