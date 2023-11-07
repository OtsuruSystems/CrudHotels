package com.mx.hotels.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.hotels.dao.IHotelDao;
import com.mx.hotels.model.Hotel;
import com.mx.hotels.model.HotelRooms;
import com.mx.hotels.model.Room;
import com.mx.hotels.services.client.RoomsFeingClient;

@Service
public class HotelServiceImpl implements IHotelService {
	
	@Autowired
	private IHotelDao hotelDao;
	
	/*@Autowired
	private RestTemplate clientRest;*/
	
	@Autowired
	private RoomsFeingClient roomFeinclient;

	@Override
	public List<Hotel> search() {
		return (List<Hotel>) hotelDao.findAll();
	}

	@Override
	public HotelRooms searchHotelById(Long hotelId) {
	HotelRooms response = new HotelRooms();
	Optional<Hotel> hotel = hotelDao.findById(hotelId);
	
	//RestTemplate
	/*Map<String, Long> pathVariable = new HashMap<String, Long>();
	pathVariable.put("id", hotelId);
	List<Room> rooms = Arrays.asList(clientRest.getForObject("http://localhost:8081/rooms/{id}",Room[].class,pathVariable));*/
	
	List<Room> rooms = roomFeinclient.searchHotelById(hotelId);
	
	response.setHotelId(hotel.get().getHotelId());
	response.setHotelName(hotel.get().getHotelName());
	response.setHotelAddress(hotel.get().getHotelAddress());
	response.setRooms(rooms);
		return response;
	}

	@Override
	public HotelRooms searchHotelByIdAlternative(Long hotelId) {
		HotelRooms response = new HotelRooms();
		Optional<Hotel> hotel = hotelDao.findById(hotelId);
				
		response.setHotelId(hotel.get().getHotelId());
		response.setHotelName(hotel.get().getHotelName());
		response.setHotelAddress(hotel.get().getHotelAddress());

		return response;
	}
}
