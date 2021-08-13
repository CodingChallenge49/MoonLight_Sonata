package com.db.employeemood.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.db.employeemood.model.MoodHistory;
import com.db.employeemood.repository.MoodHistoryRepository;

@Service
public class MoodHistoryService {
	@Autowired
	MoodHistoryRepository moodHistoryRepository;
	
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
	
	public List<String> getAllHashtags(){
		List<String> hashtagsResponse = (List)moodHistoryRepository.findAllHashtags();
		return hashtagsResponse;
	}
	
	public List<String> getTopDailyHashtags(String date){
		List<String> hashtagsResponse = (List)moodHistoryRepository.findTopDailyHashtags(date);
		return hashtagsResponse;
	}
	
	public List<MoodHistory> getMoodsByHashtag(String hashtag){
		List<MoodHistory> moodsByHashtag = (List)moodHistoryRepository.findByHashtag(hashtag);
		return moodsByHashtag;
	}
}
