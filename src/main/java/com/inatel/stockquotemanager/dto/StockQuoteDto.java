package com.inatel.stockquotemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.inatel.stockquotemanager.model.Quote;

public class StockQuoteDto {
	private String id;
	
	Map<String, String> quotes = new HashMap<String, String>();
	
	public StockQuoteDto(List<Quote> quotes, String stockId) {
		this.id = stockId;
		mapQuotes(quotes);
	}

	public String getId() {
		return id;
	}
	
	public Map<String, String> getQuotes(){
		return quotes;
	}
	
	public void mapQuotes(List<Quote> quotes) {
		quotes.forEach(quote -> {
			LocalDate date = quote.getDate();
			BigDecimal value = quote.getValue();		
			
			this.quotes.put(date.toString(), value.toBigInteger().toString());
		});
	}
}

