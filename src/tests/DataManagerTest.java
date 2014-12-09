package tests;
import app.*;
import interfaces.GuestInterface;
import interfaces.GuestReservationInterface;
import interfaces.ManagerInterface;
import interfaces.ReservationInterface;
import interfaces.RoomInterface;
import interfaces.RoomReservationInterface;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

import app.DataManager;


public class DataManagerTest
{
	DataManager m;
	
	public DataManagerTest()
	{
		m = new DataManager();
		
		//populate with test data.
		//4 reservations:
		
		//Guest 1 makes reservation 1.
		//reservation 1 reserves rooms 101 & 102 with 1 & 2 occupants.
		//reservation 1 is booked on the 1st of January the the 4th of January

		//Guest 2 makes reservation 2.
		//reservation 2 reserves room 105 with 2 occupants.
		//reservation 2 is booked on the 27th of April the the 30th of April

		//Guest 3 makes reservation 3.
		//reservation 3 reserves rooms 109 with 1 occupant.
		//reservation 3 is booked on the 13 of June the the 18th of June

		//Guest 4 makes reservation 4.
		//reservation 4 reserves rooms 110 with 3 occupants.
		//reservation 4 is booked on the 9th of July the the 13th of July
		
		//create tests
		m.addGuest(new Guest(1, "Jane Doe", "087123457", "Waterford", "jane@email.com"));
		m.addGuest(new Guest(2, "John Doe", "087123456", "Waterford", "john@email.com"));
		m.addGuest(new Guest(3, "Alice", "123456", "carlow", "alice@email.com"));
		m.addGuest(new Guest(4, "Bob", "123456", "Dublin", "bob@email.com"));
		
		//create rooms
		m.addRoom(new Room(101,50.50,2)); //(room_no, price, capacity)
		m.addRoom(new Room(102,25,3));
		m.addRoom(new Room(103,40,1));
		m.addRoom(new Room(104,60,4));
		m.addRoom(new Room(105,70,3));
		m.addRoom(new Room(106,90,2));
		m.addRoom(new Room(107,33.50,2));
		m.addRoom(new Room(108,12.50,4));
		m.addRoom(new Room(109,99.99,1));
		m.addRoom(new Room(110,66.60,4));
		
		//create the reservations
		m.addReservation(new Reservation(1, new Date(115, 1, 1), new Date(115, 1, 4)));
		m.addReservation(new Reservation(2, new Date(115, 4, 27), new Date(115, 4, 30)));
		m.addReservation(new Reservation(3, new Date(115, 6, 13), new Date(115, 6, 18)));
		m.addReservation(new Reservation(4, new Date(115, 7, 9), new Date(115, 6, 13)));
		
		//create the room reservations
		m.addRoomReservation(new RoomReservation(101,1,1)); //(room_no, res_no, num_occupants)
		m.addRoomReservation(new RoomReservation(102,1,2));
		m.addRoomReservation(new RoomReservation(105,2,2));
		m.addRoomReservation(new RoomReservation(109,3,1));
		m.addRoomReservation(new RoomReservation(110,4,3));
		
		//create the guest reservations
		m.addGuestReservation(new GuestReservation(1, 1)); //(res_id, guest_id)
		m.addGuestReservation(new GuestReservation(2, 2));
		m.addGuestReservation(new GuestReservation(3, 3));
		m.addGuestReservation(new GuestReservation(4, 4));
		
	}
	
	public void testAddValidReservation()
	{
		Reservation newReservation = new Reservation(5, new Date(115, 2, 1), new Date(115, 2, 3));
		RoomReservation newRoomReservation = new RoomReservation(101, 5, 2);
		GuestReservation newGuestReservation = new GuestReservation(1, 5);
		
		m.addReservation(newReservation);
		m.addRoomReservation(newRoomReservation);
		m.addGuestReservation(newGuestReservation);
		
		assertEquals(newReservation, m.getReservation(5));
	}
	
	public void testAddInvalidReservation()
	{
		Reservation newReservation = new Reservation(5, new Date(115, 1, 1), new Date(115, 1, 4));
		RoomReservation newRoomReservation = new RoomReservation(101, 5, 2);
		GuestReservation newGuestReservation = new GuestReservation(1, 5);
		
		assertFalse(m.createFullReservation(newReservation, newGuestReservation.getGuestId(), new RoomReservation[]{newRoomReservation}));
	}
	
	public void testEditReservation()
	{
		
	}
	
	public void testCancelReservation()
	{
		
	}
	
	public void testNewGuest()
	{
		
	}
	
	public void testCheckIfRoomFree()
	{
		//                            (y,m,d)
		// room 101 is booked between (115, 1, 1) and (115, 1, 4)
		int room_id = 101;
		Date check_in_date = new Date(115, 1, 3);
		Date check_out_date = new Date(115, 1, 5);
		assertFalse(m.checkIfRoomFree(room_id, check_in_date, check_out_date));
		
		room_id = 108;
		assertTrue(m.checkIfRoomFree(room_id, check_in_date, check_in_date));
		//room 108 has no reservations...
	}
	
	public void testGetReservationPrice()
	{
		
	}
	
}
