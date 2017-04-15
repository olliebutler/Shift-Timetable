package com.ollie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	

	private Shift() {}

	public Shift(String date, String shiftType) {
		this.date = date;
		this.shiftType = shiftType;
	
	}
}
