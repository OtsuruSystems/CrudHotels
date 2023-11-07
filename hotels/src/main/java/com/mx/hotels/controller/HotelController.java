package com.mx.hotels.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mx.hotels.config.HotelsServiceConfiguration;
import com.mx.hotels.model.Hotel;
import com.mx.hotels.model.HotelRooms;
import com.mx.hotels.model.PropertiesHotels;
import com.mx.hotels.services.IHotelService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class HotelController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	
	@Autowired
	private IHotelService service;
	
	@Autowired
	private HotelsServiceConfiguration configHotels;
	
	@GetMapping("hotels")
	public List<Hotel> search(){
		logger.info("Inicio de metodosearch()");
		return (List<Hotel>) this.service.search();	
	}
	
	@GetMapping("hotels/{hotelId}")
	//@CircuitBreaker(name="searchHotelByIdSupportCB", fallbackMethod = "searchHotelByIdAlternative")
	@Retry(name="searchHotelByIdSupportCBRetry", fallbackMethod = "searchHotelByIdAlternatives")
	public HotelRooms searchHotelById(@PathVariable Long hotelId){
		logger.info("Inicio de searchHotelById()");
		return this.service.searchHotelById(hotelId);	
	}


		public HotelRooms searchHotelByIdAlternatives(@PathVariable Long hotelId, Throwable thr){
			logger.info("Inicio de searchHotelByIdAlternatives()");
		return this.service.searchHotelByIdAlternative(hotelId);	
	}
	
	@GetMapping("/hotels/read/properties")
	public String getPropertiesHotels() throws JsonProcessingException{
		logger.info("Inicio de getPropertiesHotels()");
		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();
		PropertiesHotels propertiesHotels = new PropertiesHotels(configHotels.getMsg(),configHotels.getBuildVersion(),
				configHotels.getMailDetails());
		String jsonString = owj.writeValueAsString(propertiesHotels);
		return jsonString;
	}
	
}
