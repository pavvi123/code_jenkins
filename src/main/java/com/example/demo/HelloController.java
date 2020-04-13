package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRepo;

@RestController
public class HelloController {
	
	@Autowired
	private EmployeeRepo emprepo;
	
	@RequestMapping("/hello")
	public String getName(){
		save();
		return "Welcome  every  to jenkinsss";
		
	}
	
	public void save(){
		Employee e = new Employee();
		e.setEmail("Email");
		e.setId(2L);
		e.setName("test");
		e.setNum(100L);
		emprepo.save(e);
	}
	
	@RequestMapping("/emp")
	public List<Employee> getEmployee(){
		return emprepo.findAll();
	}

}
