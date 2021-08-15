package com.db.employeemood.response;

public class PiechartData {
	private int numPeople;
	private String rating;
	public PiechartData() {
	}
	public PiechartData(int numPeople, String rating) {
		super();
		this.numPeople = numPeople;
		this.rating = rating;
	}
	public int getNumPeople() {
		return numPeople;
	}
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
}
