package mcl.test.HibernateRestApi.service;

import java.util.List;

import mcl.test.HibernateRestApi.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void delete(int id);
	

}
