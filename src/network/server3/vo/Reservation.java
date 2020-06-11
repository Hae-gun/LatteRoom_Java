package network.server3.vo;

import java.sql.Date;

public class Reservation {
	private String 	reservNo;
	private String 	roomNo;
	private Date 	startDate;
	private Date 	endDate;
	private String 	roomName;
	private String 	roomSSID;
	private String 	imgUrl;
	
	
	public String getReservNo() {
		return reservNo;
	}
	
	public void setReservNo(String reservNo) {
		this.reservNo = reservNo;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String getRoomSSID() {
		return roomSSID;
	}
	
	public void setRoomSSID(String roomSSID) {
		this.roomSSID = roomSSID;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "Reservation [reservNo=" + reservNo + ", roomNo=" + roomNo + ", startDate=" + startDate + ", endDate="
				+ endDate + ", roomName=" + roomName + ", roomSSID=" + roomSSID + ", imgUrl=" + imgUrl + "]";
	}
	
}
