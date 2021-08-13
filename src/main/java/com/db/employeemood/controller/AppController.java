package com.db.employeemood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.employeemood.model.Employee;
import com.db.employeemood.model.MoodHistory;
import com.db.employeemood.service.EmployeeService;
import com.db.employeemood.service.MoodHistoryService;

@RestController
public class AppController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	MoodHistoryService moodHistoryService;

	@PostMapping("/saveMoodHistory")
	private ResponseEntity<MoodHistory> saveMoodHistory(@RequestBody MoodHistory moodHistory) {
		MoodHistory moodHistoryResponse = moodHistoryService.saveMoodHistory(moodHistory);
		return new ResponseEntity<MoodHistory>(moodHistoryResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getAllHistory/{date}")
	private ResponseEntity<List<MoodHistory>> getAllHistory(@PathVariable("date") String date){
		List<MoodHistory> moodHistory = moodHistoryService.getAllHistory(date);
		return new ResponseEntity<List<MoodHistory>>(moodHistory,HttpStatus.OK);
	}
	
	@GetMapping("/getTopHistory/{noHistory}")
	private ResponseEntity<List<MoodHistory>> getTopHistory(@PathVariable("noHistory") int noHistory){
		List<MoodHistory> moodHistory = moodHistoryService.getTopHistory(noHistory);
		return new ResponseEntity<List<MoodHistory>>(moodHistory,HttpStatus.OK);
	}
	
	@GetMapping("/getAllHashtags")
	private ResponseEntity<List<String>> getAllHashtags(){
		List<String> hashtags = moodHistoryService.getAllHashtags();
		return new ResponseEntity<List<String>>(hashtags,HttpStatus.OK);
	}
	
	@GetMapping("/getTopDailyHashtags/{date}")
	private ResponseEntity<List<String>> getTopDailyHashtags(@PathVariable("date") String date){
		List<String> hashtags = moodHistoryService.getTopDailyHashtags(date);
		return new ResponseEntity<List<String>>(hashtags,HttpStatus.OK);
	}
	
	@GetMapping("/getMoodsByHashtag/{hashtag}")
	private ResponseEntity<List<MoodHistory>> getMoodsByHashtag(@PathVariable("hashtag") String hashtag){
		List<MoodHistory> hashtags = moodHistoryService.getMoodsByHashtag(hashtag);
		return new ResponseEntity<List<MoodHistory>>(hashtags,HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmployee")
	private ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> employeeResponse = employeeService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(employeeResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getEmployeeByEmail/{email}")
	private ResponseEntity<Employee> getEmployeeByEmail(@PathVariable("email") String email){
		Employee employeeResponse = employeeService.getEmployeeByEmail(email);
		return new ResponseEntity<Employee>(employeeResponse,HttpStatus.OK);
	}
	
	@PostMapping("/saveEmployee")
	private ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee employeeResponse = employeeService.saveEmployee(employee);
		return new ResponseEntity<Employee>(employeeResponse,HttpStatus.OK);
	}

}
