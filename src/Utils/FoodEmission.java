package Utils;
import java.util.Date;

public class FoodEmission {
	String username;
	String foodType;
	float foodQuantity;
	float carbonQuantity;
	Date date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public float getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(float foodQuantity) {
		this.foodQuantity = foodQuantity;
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
