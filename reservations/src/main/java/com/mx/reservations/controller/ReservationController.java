package com.mx.reservations.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mx.reservations.config.ReservationsServiceConfiguration;
import com.mx.reservations.model.PropertiesReservations;
import com.mx.reservations.model.Reservation;
import com.mx.reservations.services.IReservationService;

@RestController
public class ReservationController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	private IReservationService service;
	
	@Autowired
	private ReservationsServiceConfiguration configReservations;

	@GetMapping("reservations")
	public List<Reservation> search(){
		logger.info("Inicio de search()");
		return (List<Reservation>) this.service.search();	
	}
	
	@GetMapping("/reservations/read/properties")
	public String getPropertiesReservations() throws JsonProcessingException{
		logger.info("Inicio de getPropertiesReservations()");
		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();
		PropertiesReservations propertiesReservations = new PropertiesReservations(configReservations.getMsg(),configReservations.getBuildVersion(),
				configReservations.getMailDetails());
		String jsonString = owj.writeValueAsString(propertiesReservations);
		return jsonString;
	}

}
