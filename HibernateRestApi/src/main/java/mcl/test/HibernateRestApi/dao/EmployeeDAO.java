package mcl.test.HibernateRestApi.dao;

import java.util.List;

import mcl.test.HibernateRestApi.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void delete(int id);
	
	
}
