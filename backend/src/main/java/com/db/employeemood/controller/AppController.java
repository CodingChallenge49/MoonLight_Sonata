package com.db.employeemood.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.db.employeemood.model.Employee;
import com.db.employeemood.model.MoodHistory;
import com.db.employeemood.response.AllHashtagsResponse;
import com.db.employeemood.response.HashtagCount;
import com.db.employeemood.response.PiechartData;
import com.db.employeemood.service.EmployeeService;
import com.db.employeemood.service.MoodHistoryService;

@RestController
@CrossOrigin
public class AppController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	MoodHistoryService moodHistoryService;

	@GetMapping("/")
	private ResponseEntity<String> hello(){
		return new ResponseEntity<String>("Hi Welcome",HttpStatus.OK);
	}
	
	@PostMapping("/saveMoodHistory")
	private ResponseEntity<MoodHistory> saveMoodHistory(@RequestBody MoodHistory moodHistory) {
		MoodHistory moodHistoryResponse = moodHistoryService.saveMoodHistory(moodHistory);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moodHistoryResponse.getDateTime()));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moodHistory.getDateTime()));
//		System.out.println(moodHistory.getDateTime());
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
	private ResponseEntity<List<AllHashtagsResponse>> getAllHashtags(){
		List<AllHashtagsResponse> hashtags = moodHistoryService.getAllHashtags();
		return new ResponseEntity<List<AllHashtagsResponse>>(hashtags,HttpStatus.OK);
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
	
	@GetMapping("/getCountByRatingGroup/{date}")
	private ResponseEntity<List<PiechartData>> getCountByRatingGroup(@PathVariable("date") String date){
		List<PiechartData> dataResponse = moodHistoryService.getCountByRatingGroup(date);
		return new ResponseEntity<List<PiechartData>>(dataResponse,HttpStatus.OK);
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
	
	@GetMapping("/getCountByHashtag")
	private ResponseEntity<List<HashtagCount>> getCountByHashtag() {
		List<HashtagCount> list = moodHistoryService.getCountByHashtag();
		return new ResponseEntity<List<HashtagCount>>(list, HttpStatus.OK);
	}
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer init() {
	    return new Jackson2ObjectMapperBuilderCustomizer() {
	        @Override
	        public void customize(Jackson2ObjectMapperBuilder builder) {
	            builder.timeZone(TimeZone.getDefault());
	        }
	    };
	}

}
