package com.inatel.stockquotemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Quote {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stockId;
	private BigDecimal value;
	private LocalDate date;		
	
	
	
	public Quote() {
	}

	public Quote(String stockId, BigDecimal value, LocalDate date) {
		this.stockId = stockId;
		this.value = value;
		this.date = date;
	}
	
	public String getStockId() {
		return stockId;
	}	

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getValue() {
		return value;
	}
	
	
	
	
}
