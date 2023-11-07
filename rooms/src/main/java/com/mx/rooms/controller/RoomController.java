package com.mx.rooms.controller;

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
import com.mx.rooms.config.RoomsServiceConfiguration;
import com.mx.rooms.model.PropertiesRooms;
import com.mx.rooms.model.Room;
import com.mx.rooms.services.IRoomService;

@RestController
public class RoomController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class); 

	@Autowired
	private IRoomService service;
	
    @Autowired
	private RoomsServiceConfiguration configRooms;
	
	@GetMapping("rooms")
	public List<Room> search(){
		logger.info("Inicio de search()");
		return (List<Room>) this.service.search();	
	}
	
	@GetMapping("rooms/{id}")
	public List<Room> searchhotelId(@PathVariable Long id){
		logger.info("Inicio de searchhotelId()");
		return (List<Room>) this.service.searchRoomByHotelId(id);	
	}
	
	@GetMapping("/rooms/read/properties")
	public String getPropertiesRooms() throws JsonProcessingException{
		logger.info("Inicio de getPropertiesRooms()");
		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();
		PropertiesRooms propertiesRooms = new PropertiesRooms(configRooms.getMsg(),configRooms.getBuildVersion(),
				configRooms.getMailDetails());
		String jsonString = owj.writeValueAsString(propertiesRooms);
		return jsonString;
	}
		
}
