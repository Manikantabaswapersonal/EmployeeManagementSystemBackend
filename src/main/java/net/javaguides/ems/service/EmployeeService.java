package net.javaguides.ems.service;

import java.util.List;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

public interface EmployeeService {
EmployeeDto createEmployee(EmployeeDto employeeDto);
EmployeeDto getEmployeeById(Long id);
List<EmployeeDto> getAllEmployees();
EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee);
void deletById(Long id);
}
