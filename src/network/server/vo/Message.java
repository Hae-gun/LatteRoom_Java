package network.server.vo;

public class Message {
	private String clientNo;
	private String code1;
	private String code2;
	private String jsonData;
	
	
	// =================================================
	// Constructor
	public Message() {
		
	}
	
	public Message(String clientNo, String code, String jsonData) {
		this.clientNo = clientNo;
		this.code1 = code;
		this.jsonData = jsonData;
	}
	
	public Message(String clientNo, String code, String subCode, String jsonData) {
		this(clientNo, code, jsonData);
		this.code2 = subCode;
	}
	
	// =================================================
	// get, set
	public String getClientNo() {
		return clientNo;
	}
	
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	
	public String getCode1() {
		return code1;
	}
	
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	
	public String getCode2() {
		return code2;
	}
	
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	
	public String getJsonData() {
		return jsonData;
	}
	
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	@Override
	public String toString() {
		return "Message [clientNo=" + clientNo + ", code1=" + code1 + ", code2=" + code2 + ", jsonData=" + jsonData
				+ "]";
	}
	
}
