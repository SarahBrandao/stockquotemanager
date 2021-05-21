package com.inatel.stockquotemanager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.inatel.stockquotemanager.model.Quote;
import com.inatel.stockquotemanager.repository.QuoteRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class QuoteServiceTest {

	@MockBean
	private QuoteRepository repository;
	
	@InjectMocks
	private QuoteService service;
	
	
	@BeforeEach
	public void before() {
		this.service = new QuoteService(repository);
	}
	
	@Test
	void testShouldFindByStockId() {
		String stockId = "StockTest";
		List <Quote> quotes = new ArrayList<>();
		quotes.add(new Quote(stockId, new BigDecimal("555"), LocalDate.parse("2021-05-21")));
		quotes.add(new Quote(stockId, new BigDecimal("111"), LocalDate.parse("2022-05-21")));
		Mockito.when(repository.findByStockId(stockId)).thenReturn(quotes);
		List<Quote> list = service.findByStockId(stockId);
		Assert.assertEquals(quotes.size(), list.size());
	}
}
