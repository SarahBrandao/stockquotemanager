package com.inatel.stockquotemanager.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.inatel.stockquotemanager.dto.StockDto;
import com.inatel.stockquotemanager.service.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
class QuoteControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StockService service;
	
	private List<StockDto> stocks;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
