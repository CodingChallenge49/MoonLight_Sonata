package com.db.employeemood.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.db.employeemood.model.Employee;
import com.db.employeemood.model.MoodHistory;
import com.db.employeemood.repository.EmployeeRepository;
import com.db.employeemood.repository.MoodHistoryRepository;
import com.db.employeemood.response.PiechartData;
import com.db.employeemood.response.AllHashtagsResponse;
import com.db.employeemood.response.HashtagCount;

@Service
public class MoodHistoryService {
	@Autowired
	MoodHistoryRepository moodHistoryRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public MoodHistory saveMoodHistory(MoodHistory moodHistory) {
		moodHistoryRepository.save(moodHistory);
		return moodHistory;
	}

	public List<MoodHistory> getTopHistory(int noHistory){
		Collection<MoodHistory> collection = moodHistoryRepository.findTopHistory(noHistory);
		List<MoodHistory> moodHistory = collection.stream().collect(Collectors.toList());
		return moodHistory;
	}
	
	public List<MoodHistory> getAllHistory(String date){
		Collection<MoodHistory> collection = moodHistoryRepository.findAllHistory(date);
		List<MoodHistory> moodHistory = collection.stream().collect(Collectors.toList());
		return moodHistory;
	}
	
	public List<AllHashtagsResponse> getAllHashtags(){
		List<String> hashtagsResponse = (List)moodHistoryRepository.findAllHashtags();
		List<AllHashtagsResponse> response = new ArrayList<>();
		hashtagsResponse.forEach((hashtag)->{
			response.add(new AllHashtagsResponse(hashtag));
		});
		return response;
	}
	
	public List<String> getTopDailyHashtags(String date){
		List<String> hashtagsResponse = (List)moodHistoryRepository.findTopDailyHashtags(date);
		return hashtagsResponse;
	}
	
	public List<MoodHistory> getMoodsByHashtag(String hashtag){
		List<MoodHistory> moodsByHashtag = (List)moodHistoryRepository.findByHashtag(hashtag);
		return moodsByHashtag;
	}
	
	public List<PiechartData> getCountByRatingGroup(String date){
		List<PiechartData> dataResponse = new ArrayList<>();
		for(int i=1;i<10;i+=2) {
			int numPeople = moodHistoryRepository.findCountByRatingGroup(date, i, i+1);
			PiechartData data = new PiechartData(numPeople,"Rating " + String.valueOf(i)+"-"+String.valueOf(i+1));
			dataResponse.add(data);
		}
		return dataResponse;
	}
	
	public List<HashtagCount> getCountByHashtag() {
		List<Object[]> list = moodHistoryRepository.getCountByHashtag();
		List<HashtagCount> response = new ArrayList<>();
		
		list.forEach((eachData)->{
			String hashtag = String.valueOf(eachData[0]);
			int count = Integer.parseInt(String.valueOf(eachData[1]));
			response.add(new HashtagCount(hashtag,count));
		});
		return response;
	}
	
	public void sendEmail(String date,String to, String employee) {
		int count = moodHistoryRepository.findCountOfDepression(date, employee);
		System.out.println(date+to+count);
		if(count >= 3) {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom("mood.emailto.manager@gmail.com");
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject("Have a look at your employee - Employee Mood");
			simpleMailMessage.setText("Your employee " + to + " has given very low mood ratings through our portal over last week.\n You can get in touch with your employee.");
			javaMailSender.send(simpleMailMessage);
			System.out.println("Mail Sent");
		}
		
	}
	
	public String getManager(String email) {
		Employee employee = employeeRepository.findById(email).get();
		return employee.getManager_email();
	}
}
