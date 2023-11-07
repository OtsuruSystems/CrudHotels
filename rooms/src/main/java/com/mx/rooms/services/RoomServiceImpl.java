package com.mx.rooms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.rooms.dao.IRoomDao;
import com.mx.rooms.model.Room;

@Service
public class RoomServiceImpl implements IRoomService {
	
	@Autowired
	private IRoomDao roomDao;

	@Override
	public List<Room> search() {
		return (List<Room>) roomDao.findAll();
	}

	@Override
	public List<Room> searchRoomByHotelId(Long hotelId) {
		List<Room> rooms = this.roomDao.findByHotelId(hotelId);
				return rooms;
	}

}
