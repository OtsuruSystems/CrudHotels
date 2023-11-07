package com.mx.hotels.services;

import java.util.List;

import com.mx.hotels.model.Hotel;
import com.mx.hotels.model.HotelRooms;

public interface IHotelService {
	
	List<Hotel> search();
	HotelRooms searchHotelById(Long hotelId);
	HotelRooms searchHotelByIdAlternative(Long hotelId);

}
