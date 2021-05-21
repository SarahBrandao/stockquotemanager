package com.inatel.stockquotemanager.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuoteControllerTest {	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void ShouldListAllStocks() throws Exception {
		URI uri = new URI("/stock");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testShouldListOneStock() throws Exception {
		URI uri = new URI("/stock/petr4");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testShouldNotListOneStock() throws Exception {
		URI uri = new URI("/stock/idInvalido");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void testShouldCreateAStockQuote() throws Exception {
		URI uri = new URI("/stock");
		
		Map<String, String> quotes = new HashMap<String, String>();
		quotes.put("2021-05-11", "333");
		JSONObject json = new JSONObject(quotes);
		JSONObject quote = new JSONObject();
		quote.put("quotes", json);
		quote.put("id", "petr4");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(quote.toString()).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));		
	}
	
	@Test
	public void testShouldNotCreateAStockQuote() throws Exception {
		URI uri = new URI("/stock");
		
		Map<String, String> quotes = new HashMap<String, String>();
		quotes.put("2021-05-11", "333");
		JSONObject json = new JSONObject(quotes);
		JSONObject quote = new JSONObject();
		quote.put("quotes", json);
		quote.put("id", "teste");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(quote.toString()).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(404));		
	}
	

}
