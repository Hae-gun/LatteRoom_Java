package network.server4.service;

import java.util.List;

import network.server4.dao.ReservDAO;
import network.server4.vo.Reservation;

public class ReservService {
	
	public List<Reservation> getReservInfo(String userNo) {
		
		ReservDAO rdao = new ReservDAO();
		
		List<Reservation> reservInfo = rdao.selectReserv(userNo);

		return reservInfo;
		
	}
	
}
