package securitydisplay;

import javafx.beans.property.SimpleStringProperty;

public class Visiter {
	private String visiter;
	private String date;
	private SimpleStringProperty image;
	
	public Visiter(String visiter, String date, String image) {
		this.visiter = visiter;
		this.date = date;
		this.image = new SimpleStringProperty(image);
	}
	
	public String getVisiter() {
		return visiter;
	}
	
	public void setVisiter(String visiter) {
		this.visiter = visiter;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getImage() {
		return image.get();
	}
	
	public void setImage(String image) {
		this.image.set(image);
	}
}
