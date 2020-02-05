package mcl.test.HibernateRestApi.rest;

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

import mcl.test.HibernateRestApi.entity.Employee;
import mcl.test.HibernateRestApi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {

		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> FindAll(){
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		
		if (employee == null) {
			throw new RuntimeException("Employee id not found");
		}
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee){
		// with id == 0, the employee will always be created instead of update
		employee.setId(0);
		employeeService.save(employee);
		
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee){
		
		employeeService.save(employee);
		
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		
		if (employee == null) {
			throw new RuntimeException("Employee id not found");
		}
		
		employeeService.delete(employeeId);
		return String.format("Employee deleted: %s", employee.toString());
	}
}
