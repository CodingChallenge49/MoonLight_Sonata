package com.db.employeemood.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.db.employeemood.model.MoodHistory;

@Repository
public interface MoodHistoryRepository extends JpaRepository<MoodHistory, Integer> {
	@Query(
			value = "SELECT * FROM mood_history ORDER BY date_time DESC LIMIT ?1", 
			nativeQuery = true
			)
	Collection<MoodHistory> findTopHistory(Integer noHistory);
	
	@Query(
			value = "SELECT * FROM mood_history WHERE DATE(date_time)=?1", 
			nativeQuery = true
			)
	Collection<MoodHistory> findAllHistory(String date);
	
	
	@Query(
			value = "SELECT DISTINCT hashtag FROM mood_history WHERE hashtag IS NOT NULL", 
			nativeQuery = true
			)
	Collection<String> findAllHashtags();
	
	@Query(
			value = "select hashtag from mood_history where DATE(date_time)=?1 group by hashtag order by count(*) DESC LIMIT 5", 
			nativeQuery = true
			)
	Collection<String> findTopDailyHashtags(String date);
	
	List<MoodHistory> findByHashtag(String hashtag);

	
}
