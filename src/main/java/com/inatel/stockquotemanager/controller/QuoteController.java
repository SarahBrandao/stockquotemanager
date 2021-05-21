package com.inatel.stockquotemanager.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.inatel.stockquotemanager.dto.StockDto;
import com.inatel.stockquotemanager.dto.StockQuoteDto;
import com.inatel.stockquotemanager.form.QuoteForm;
import com.inatel.stockquotemanager.model.Quote;
import com.inatel.stockquotemanager.service.QuoteService;
import com.inatel.stockquotemanager.service.StockService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/stock")
public class QuoteController {	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private QuoteService quoteService;
	
	
	//Listing All the stocks with their quotes
	@GetMapping
	public ResponseEntity<List<StockQuoteDto>> ListingAll(){			
		
		log.info("Listing all the stocks with the quotes.");
		List<StockDto> stocks = stockService.getAll();
		List<StockQuoteDto> stockQuoteDto = new ArrayList<StockQuoteDto>();
		
		stocks.forEach(stock -> {
			List<Quote> quotes = quoteService.findByStockId(stock.getId());
			stockQuoteDto.add(new StockQuoteDto(quotes, stock.getId()));
		});
		
		//return stockQuoteDto;		
		return ResponseEntity.status(200).body(stockQuoteDto);
	}
	
	//Listing one stock at a time
	@GetMapping("/{stockId}")
	public ResponseEntity<?> ListingOne(@PathVariable String stockId) {        
		
		List<Quote> quote = quoteService.findByStockId(stockId);
		
		if (quote.size() == 0) {
			log.error("The Stock "+ stockId +" doesn't exist.");
			JSONObject message = new JSONObject();
			message.put("Warning: ", "\"The Stock \"+ stockId +\" doesn't exist.\"");
			return ResponseEntity.status(404).body(message.toString());	
		
		}
		else {
			log.info("The quotes of "+stockId+" Stock were returned correctly.");
			return ResponseEntity.ok(new StockQuoteDto(quote, stockId));
		}				
	}	
	
	//Creating Quotes in a Stock that already exists.
	@PostMapping
	public ResponseEntity<?> CreateAStockQuote(@RequestBody QuoteForm form, UriComponentsBuilder uriBuilder) {
		StockDto stockdto = stockService.getById(form.getId());
		
		if (stockdto != null) {					
			log.info("The quotes are being created correctly.");
			List<Quote> quotes = form.convertQuote();
			quoteService.saving(quotes);		
			URI uri = uriBuilder.path("/quote/{stockId}").buildAndExpand(form.getId()).toUri();		
			List<Quote> listQuotes = quoteService.findByStockId(form.getId());
			
			return ResponseEntity.created(uri).body(new StockQuoteDto(listQuotes, form.getId()));
		}		
		
		log.error("The Stock doesn't exist because of it the quotes aren't being created.");
		JSONObject message = new JSONObject();
		message.put("Warning: ", "The Stock doesn't exist because of it the quotes aren't being created.");
		return ResponseEntity.status(404).body(message.toString());	
	}
}
