package Utils;
import java.util.Date;

public class TransportEmission {
	String username;
	String transportType;
	float distance;
	float carbonQuantity;
	Date date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public float getCarbonQuantity() {
		return carbonQuantity;
	}
	public void setCarbonQuantity(float carbonQuantity) {
		this.carbonQuantity = carbonQuantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
