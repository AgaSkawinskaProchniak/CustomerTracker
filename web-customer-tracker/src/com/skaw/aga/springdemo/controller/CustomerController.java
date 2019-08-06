package com.skaw.aga.springdemo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skaw.aga.springdemo.entity.Customer;
import com.skaw.aga.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer>customers = customerService.getCustomers();
		
		theModel.addAttribute("customers", customers);
				
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	private String showFormForAdd(Model theModel) {
		
		Customer customer = new Customer();
		theModel.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
		public String updateCustomer(@ModelAttribute("customerId")int theId,Model theModel) {
			
			Customer customer = customerService.getCustomer(theId);
			theModel.addAttribute("customer",customer);
			 		
			return "customer-form";
		}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId")int theId) {
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	}
	


