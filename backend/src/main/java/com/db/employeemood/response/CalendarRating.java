package com.db.employeemood.response;

public class CalendarRating {
	private String date;
	private String rating;
	public CalendarRating() {
	}
	public CalendarRating(String date, String rating) {
		super();
		this.date = date;
		this.rating = rating;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "CalendarRating [date=" + date + ", rating=" + rating + "]";
	}
}
