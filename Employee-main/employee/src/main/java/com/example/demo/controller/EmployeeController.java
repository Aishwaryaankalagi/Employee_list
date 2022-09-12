package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResouceNotFoundException;
import com.example.demo.model.Employee_info;
import com.example.demo.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")


public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee_info> getAllEmployees(){
		return employeeRepository.findAll();
	}

	
		@PostMapping("/employees")
		public Employee_info createEmployee(@RequestBody Employee_info employee) {
			return employeeRepository.save(employee);
		}
		
		// get employee by id rest 
		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee_info> getEmployeeById(@PathVariable Long id) {
			Employee_info employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResouceNotFoundException("Employee not exist with id :" + id));
			return ResponseEntity.ok(employee);
		}
		
		// update employee rest
		
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee_info> updateEmployee(@PathVariable Long id, @RequestBody Employee_info employeeDetails){
			Employee_info employee = employeeRepository.findById(id).
					orElseThrow(() ->  new ResouceNotFoundException("Employee not exist with id :" + id));
			
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmailId(employeeDetails.getEmailId());
			
			Employee_info updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		// delete employee rest
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			Employee_info employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResouceNotFoundException("Employee not exist with id :" + id));
			
			employeeRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
	}










































