package com.mycompany.myapp.dto;

public class LcdText {
	private String text;
	private static LcdText lcd = new LcdText();
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public static LcdText getLcd() {
		return lcd;
	}
	
}
