package net.javaguides.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.maptoEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
			String errorMessage = "Employee does not exist for the given id: " + id;
			logger.error(errorMessage);

			return new ResourceNotFoundException(errorMessage);
		});
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employee = employeeRepository.findAll();
		return employee.stream().map((emp) -> EmployeeMapper.mapToEmployeeDto(emp)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> {
			String error = "Employee does not exist for the given id: " + id;
			logger.error(error);
			return new ResourceNotFoundException(error);
		});
	
		emp.setFirstName(updatedEmployee.getFirstName());
		emp.setLastName(updatedEmployee.getLastName());
		emp.setEmail(updatedEmployee.getEmail());
		Employee employeUpdateValues = employeeRepository.save(emp);
		return EmployeeMapper.mapToEmployeeDto(employeUpdateValues);
	}

	@Override
	public void deletById(Long id) {
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> {
			String error = "Employee does not exist for the given id: " + id;
			logger.error(error);
			
			return new ResourceNotFoundException(error);
			
		}
		);
		employeeRepository.deleteById(id);

}
}