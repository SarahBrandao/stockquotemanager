package com.inatel.stockquotemanager.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.inatel.stockquotemanager.model.Quote;

@Component
public class QuoteForm {	
 
	@NotNull @NotEmpty @Length(min = 5)
	private String id;
	
	@NotNull @NotEmpty 
	Map<String, String> quotes = new HashMap<String, String>();
	
	public String getId() {
		return id;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}

	public List<Quote> convertQuote() {
		
		List<Quote> quotesList = new ArrayList<>();

        for (Map.Entry<String, String> quote : this.quotes.entrySet()) {
            LocalDate date = LocalDate.parse(quote.getKey());
            BigDecimal value = new BigDecimal(quote.getValue());

            quotesList.add(new Quote(this.id, value, date));
        }

        return quotesList;
	}
}
