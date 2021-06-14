package com.luv2code.springdemo.rest;

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

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//autowired the customerservice
	@Autowired
	private CustomerService customerService;
	
	//add mapping
	@GetMapping("/customers")
	public List<Customer> getcustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getcustomer(@PathVariable int customerId ){
		Customer customer = customerService.getCustomer(customerId);
		if(customer == null) {
			throw new CustomerNotFoundException("customer id not found " +customerId  );
		}
		return customer;
	}
	
	//add customer to the database
	@PostMapping("/customers")
	public Customer addcustomer(@RequestBody Customer thecustomer) {
		thecustomer.setId(0);
		customerService.saveCustomer(thecustomer);
		return thecustomer;
	}
	
	//put mapping to updat the customers
	@PutMapping("/customers")
	public Customer updatecustomer(@RequestBody Customer thecustomer) {
		customerService.saveCustomer(thecustomer);
		return thecustomer;
	}
	
	
	@DeleteMapping("/customers/{customerid}")
	public String deletecustomer(@PathVariable int customerid) {
		Customer cs = customerService.getCustomer(customerid);
		if(cs == null) {
		throw new CustomerNotFoundException("customer id not found " +cs  );
		}
		customerService.deleteCustomer(customerid);
		return "customer " +customerid + " deleted" ;
	}
}
