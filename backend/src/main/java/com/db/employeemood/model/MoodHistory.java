package com.db.employeemood.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="mood_history")
public class MoodHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="date_time")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;
	@Column(name = "name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="rating")
	private int rating;
	@Column(name="hashtag")
	private String hashtag;
	@Column(name="mood_justification")
	private String moodJustification;
	
	public MoodHistory() {
		
	}
	public MoodHistory(int id, Date dateTime, String name, String email, int rating, String hashtag, String moodJustification) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.name = name;
		this.email = email;
		this.rating = rating;
		this.moodJustification = moodJustification;
		this.hashtag = hashtag;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getMoodJustification() {
		return moodJustification;
	}
	public void setMoodJustification(String moodJustification) {
		this.moodJustification = moodJustification;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
