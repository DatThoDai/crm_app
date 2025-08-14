package entity;

import java.time.LocalDate;

public class Jobs {
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public Jobs() {
	}
	
	public Jobs(int id, String name, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
