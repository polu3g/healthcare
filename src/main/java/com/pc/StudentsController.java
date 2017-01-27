package com.pc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.pc.entity.StudentEntity;
import com.pc.entity.Customer;

@RestController
public class StudentsController {

	@Autowired
	SQLSrvRepository sqlSrvRepository;
	
	@Autowired
	private CustomerRepository mongoRepository;
	
	@RequestMapping("/findall")
    public List findall(@RequestParam(value="name", defaultValue="World") String name) {
		List<StudentEntity> pupils = (ArrayList<StudentEntity>) sqlSrvRepository.findAll();
        return pupils;
    }
	
	@RequestMapping("/findallmongo")
    public List findallmongo(@RequestParam(value="name", defaultValue="World") String name) {
		List<Customer> pupils = (ArrayList<Customer>) mongoRepository.findAll();
        return pupils;
    }
	
}

