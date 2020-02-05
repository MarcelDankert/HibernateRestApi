package mcl.test.HibernateRestApi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mcl.test.HibernateRestApi.dao.EmployeeDAO;
import mcl.test.HibernateRestApi.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeRestController(EmployeeDAO employeeDAO) {

		this.employeeDAO = employeeDAO;
	}
	
	@GetMapping("/employees")
	public List<Employee> FindAll(){
		return employeeDAO.findAll();
	}
	
	
}