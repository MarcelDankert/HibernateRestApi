package mcl.test.HibernateRestApi.rest;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;

import mcl.test.HibernateRestApi.entity.Employee;
import mcl.test.HibernateRestApi.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@DisplayName("Write assertions for class EmployeeRestController")
class EmployeeRestControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	EmployeeRestController employeeRestController;

	MockMvc mockMvc;

	List<Employee> validEmployees;

	@BeforeEach
	void setUp() {
		validEmployees = new ArrayList<>();
		validEmployees
				.add(Employee.builder().id(0).email("Test@Test.test").firstName("Max").lastName("Mustermann").build());
		validEmployees.add(
				Employee.builder().id(1).email("Test1@Test1.test").firstName("Max1").lastName("Mustermann1").build());

		mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController).build();
	}

	@Test
	void testGetEmployeeById() throws Exception {
		when(employeeService.findById(0)).thenReturn(validEmployees.get(0));

		mockMvc.perform(get("/api/employees/" + validEmployees.get(0).getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName", is(validEmployees.get(0).getFirstName())));
	}
	
	@Test
	void testGetAllEmployees() throws Exception {
		when(employeeService.findAll()).thenReturn(validEmployees);

		mockMvc.perform(get("/api/employees/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
	            .andExpect(jsonPath("$[0].firstName", is("Max")))
	            .andExpect(jsonPath("$[1].firstName", is("Max1")));
	}
}
