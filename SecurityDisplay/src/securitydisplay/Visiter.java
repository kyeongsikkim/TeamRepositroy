package securitydisplay;

import javafx.beans.property.SimpleStringProperty;

public class Visiter {
	private SimpleStringProperty visiter;
	private SimpleStringProperty date;
	private SimpleStringProperty image;
	
	public Visiter(String visiter, String date, String image) {
		this.visiter = new SimpleStringProperty(visiter);
		this.date = new SimpleStringProperty(date);
		this.image = new SimpleStringProperty(image);
	}
	
	public String getVisiter() {
		return visiter.get();
	}
	
	public void setVisiter(String visiter) {
		this.visiter.set(visiter);
	}
	
	public String getDate() {
		return date.get();
	}
	
	public void setDate(String date) {
		this.date.set(date);
	}
	
	public String getImage() {
		return image.get();
	}
	
	public void setImage(String image) {
		this.image.set(image);
	}
}
