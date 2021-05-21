package com.inatel.stockquotemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inatel.stockquotemanager.model.Quote;
import com.inatel.stockquotemanager.repository.QuoteRepository;

@Service
public class QuoteService {	
	QuoteRepository quoteRepository;
	
	@Autowired
	public QuoteService(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

	public void saving(List<Quote> quotes) {
		quoteRepository.saveAll(quotes);
	}
	
	public List<Quote> findByStockId(String stockId){
		return quoteRepository.findByStockId(stockId);
	}
	
}
