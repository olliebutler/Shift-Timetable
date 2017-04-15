package com.ollie.Timetable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Shift {

	private @Id @GeneratedValue Long id;
	private String date;
	private String shiftType;
	
	private @Version @JsonIgnore Long version;
	
	private @ManyToOne Staff staff;
	

	private Shift() {}

	public Shift(String date, String shiftType, Staff staff) {
		this.date = date;
		this.shiftType = shiftType;
		this.staff = staff;
	
	}
}
