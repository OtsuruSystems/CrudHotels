package com.mx.rooms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mx.rooms.model.Room;

public interface IRoomDao extends CrudRepository<Room, Long>{
	public List<Room> findByHotelId(long hotelId);
}
