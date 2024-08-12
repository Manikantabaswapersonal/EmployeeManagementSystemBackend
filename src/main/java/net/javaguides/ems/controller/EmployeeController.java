package net.javaguides.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

	}
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
		EmployeeDto employeedto=employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employeedto);
	}
	
	//@GetMapping("/findAll")
@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
	List<EmployeeDto> employees=	employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id ,@RequestBody EmployeeDto employeeDto){
	EmployeeDto empDto=employeeService.updateEmployee(id, employeeDto);
		
		return ResponseEntity.ok(empDto);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		employeeService.deletById(id);
		return ResponseEntity.ok("Employee with id "+id+" Deleted Succesfully");
		
	}
	

}
