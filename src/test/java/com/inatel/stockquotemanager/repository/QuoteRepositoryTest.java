package com.inatel.stockquotemanager.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.inatel.stockquotemanager.model.Quote;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class QuoteRepositoryTest {

	@Autowired
	private QuoteRepository repository;

	
	@BeforeEach
	public void insertingQuoteInAEmptyStock() {
		List<Quote> quote = new ArrayList<>();
		quote.add(new Quote("vale5", new BigDecimal("555"), LocalDate.parse("2021-05-21")));
		repository.saveAll(quote);
	}
	
	@Test
	public void TestingById() {
		String stockName = "vale5";
		List<Quote> stockquote = repository.findByStockId(stockName);
		Assert.assertNotNull(stockquote);
		System.out.println(stockquote.get(0).getStockId());
		Assert.assertEquals(stockName, stockquote.get(0).getStockId());
	}
}
