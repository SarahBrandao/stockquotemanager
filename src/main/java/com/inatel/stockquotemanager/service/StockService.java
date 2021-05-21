package com.inatel.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.inatel.stockquotemanager.dto.StockDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {

	private String url = "http://localhost:8080";
	private RestTemplate restTemplate;

	public StockService() {
		restTemplate = new RestTemplate();
	}

	@Cacheable(value = "allStocks")
	public List<StockDto> getAll() {
		log.info("Acessing the Stocks that exists in external API.");
		StockDto[] stock = restTemplate.getForObject(url+"/stock", StockDto[].class);
		return Arrays.asList(stock);
	}

	@Cacheable(value = "stock")
	public StockDto getById(String id) {
		log.info("Acessing a Stock from external API.");
		String idURL = url + "/stock/" + id;

		StockDto dto = restTemplate.getForObject(idURL, StockDto.class);
		return dto;
	}

	public void NotificationRegister() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		JSONObject data = new JSONObject();
		data.put("host", "localhost");
		data.put("port", "8081");
		HttpEntity<String> request = new HttpEntity<String>(data.toString(), httpHeaders);
		String notificationURL = url + "/notification";
		restTemplate.postForObject(notificationURL, request, String.class);
	}
}
