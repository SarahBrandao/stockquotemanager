package com.inatel.stockquotemanager.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inatel.stockquotemanager.service.StockService;


@RestController
@RequestMapping("/stockcache")
public class CacheController {

	@Autowired
	public CacheController(StockService service) {
		service.NotificationRegister();
	}
	
	
	@DeleteMapping
	@Transactional
	@Caching(evict = { @CacheEvict(value = "allStocks", allEntries = true), @CacheEvict(value = "stock", allEntries = true) })
	public ResponseEntity<Object> cleaningCache() {
		return ResponseEntity.status(204).build();
	}
}
