package com.db.employeemood;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.employeemood.model.Employee;
import com.db.employeemood.model.MoodHistory;
import com.db.employeemood.repository.EmployeeRepository;
import com.db.employeemood.repository.MoodHistoryRepository;
import com.db.employeemood.service.EmployeeService;
import com.db.employeemood.service.MoodHistoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private MoodHistoryService moodHistoryService;
	
	@MockBean
	private MoodHistoryRepository moodHistoryRepository;
	
	@Test
	public void getEmployeeTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee("mohitbadve@gmail.com","mohit","codingchallenge49@gmail.com"),
						new Employee("vivek@gmail.com","vivek","codingchallenge49@gmail.com"))
							.collect(Collectors.toList()));
		
		assertEquals(2,employeeService.getAllEmployee().size());
	}
	
	@Test
	public void getEmployeeByEmail() {
		String email = "mohitbadve@gmail.com";
		when(employeeRepository.findById(email)).thenReturn(Optional.of(new Employee("mohitbadve@gmail.com","mohit","codingchallenge49@gmail.com")));
		System.out.println(employeeService.getEmployeeByEmail(email));
		assertEquals(email,employeeService.getEmployeeByEmail(email).getEmail());
	}
	
	@Test
	public void saveEmployee() {
		Employee employee = new Employee("mohitbadve@gmail.com","mohit","codingchallenge49@gmail.com");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee,employeeService.saveEmployee(employee));
	}
	
	@Test
	public void saveMoodHistory() {
		MoodHistory moodHistory = new MoodHistory(1,null,"hello","mohitbadve@gmail.com",3,"lastday","xcsdwe");
		when(moodHistoryRepository.save(moodHistory)).thenReturn(moodHistory);
		assertEquals(moodHistory,moodHistoryService.saveMoodHistory(moodHistory));
	}
}
