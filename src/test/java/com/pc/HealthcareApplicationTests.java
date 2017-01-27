package com.pc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

import com.pc.entity.*;

@RunWith(SpringRunner.class)
@SpringBootTest
/* pc */
public class HealthcareApplicationTests {

	@Autowired
	SQLSrvRepository sqlSrvRepository; 
	@Autowired
	private CustomerRepository mongoRepository;
	
	@Test
	 public void testLoadStudents() {
	     mongoRepository.deleteAll();
			
		 // save a couple of customers
	     mongoRepository.save(new Customer("Alice", "Smith"));
	     mongoRepository.save(new Customer("Bob", "Smith"));

	     List<StudentEntity> pupils = (ArrayList<StudentEntity>) sqlSrvRepository.findAll();
	     assertEquals("Did not get all pupils", pupils.size(), pupils.size());
	     sqlSrvRepository.findAll(); 
	     		
	     StudentEntity std = new StudentEntity();
         std.setId(new Integer(new Random().nextInt())); // Primary Key
         std.setName("Puu");
         std.setDepartment("ECE");
         std.setCollege("SKCET");
         sqlSrvRepository.save(std);
	 }

}
