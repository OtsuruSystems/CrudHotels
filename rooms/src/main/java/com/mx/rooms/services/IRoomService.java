package com.mx.rooms.services;

import java.util.List;

import com.mx.rooms.model.Room;

public interface IRoomService {
	
	List<Room> search();
	List<Room> searchRoomByHotelId(Long hotelId);

}