package com.db.employeemood.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	@Query(
			value="select count(*) from mood_history where DATE(date_time)=?1 and rating >= ?2 and rating <= ?3",
			nativeQuery = true
			)
	int findCountByRatingGroup(String date, int low, int high);
	
	@Query(value = "select hashtag as hashtag, count(*) as count  from mood_history where hashtag is NOT NULL group by hashtag order by count(*) desc", nativeQuery = true)
	List<Object[]> getCountByHashtag();
	
	@Query(
			value="select count(*) from mood_history where rating < 5 and DATEDIFF(?1,date_time) <= 7 and email = ?2",
			nativeQuery=true)
	int findCountOfDepression(String date, String email);

	
}
