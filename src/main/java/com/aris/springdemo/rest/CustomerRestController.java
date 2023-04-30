package com.aris.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aris.springdemo.entity.Customer;
import com.aris.springdemo.exceptionhandling.CustomerNotFoundException;
import com.aris.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	//fetch single customer by id
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer = customerService.getCustomer(id);
		if(customer == null) throw new CustomerNotFoundException("Customer id not found - "+id);
		return customer;
	}
	
	//add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		//setting id to 0 as saveOrUpdate method considers 0 or null value of primary kry as new record
		//also it will force method to save item rather than updating it
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}
	
	//update a customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	//delete a customer
	@DeleteMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable int id) {
		
		//best practice is to check first if customer with given id exists or not
		Customer customer = customerService.getCustomer(id);
		
		//if not exist throw error
		if(customer == null) throw new CustomerNotFoundException("Customer id not found : "+id);
		
		customerService.deleteCustomer(id);
		return "Deleted customer id : "+id;
	}
	
}
