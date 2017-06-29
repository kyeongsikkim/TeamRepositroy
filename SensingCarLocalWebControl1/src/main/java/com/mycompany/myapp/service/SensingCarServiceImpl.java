package com.mycompany.myapp.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.RaspiPin;

import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.lcd.LCD1602;
import hardware.led.RGBLedPWM;
import hardware.sensor.GasSensor;
import hardware.sensor.ThermistorSensor;

@Component
public class SensingCarServiceImpl implements SensingCarService {
//
//	PCF8591 pcf8591T = new PCF8591(0x48, PCF8591.AIN1);
////	PCF8591 pcf8591G = new PCF8591(0x48, PCF8591.AIN2);
//	private LCD1602 lcd1602;
//	private ActiveBuzzer buzzer;
//	private RGBLedPWM rgbLed;
//	private GasSensor gasSensor;
//	private ThermistorSensor thermistor;
//	private int value;
//
//	@PostConstruct
//	public void init() throws Exception {
//		lcd1602 = new LCD1602(0x27);
//		buzzer = new ActiveBuzzer(RaspiPin.GPIO_24);
//		rgbLed = new RGBLedPWM(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
////		gasSensor = new GasSensor(pcf8591G, RaspiPin.GPIO_23);
//		thermistor = new ThermistorSensor(pcf8591T);
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				try {
//					value = gasSensor.getValue();
//					Thread.sleep(1000);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		thread.start();
//	}
//
//	@Override
//	public String lcdWrite() throws Exception {
//		lcd1602.clear();
//		while (true) {
//			if (value > 60) {
//				String line0 = "gas";
//				String line1 = "detected";
//				lcd1602.write(0, 0, line0);
//				lcd1602.write(1, 0, line1);
//				return line0 + " " + line1;
//			}
//			return null;
//		}
//	}
//
//	@Override
//	public void buzzer(String status) {
//		if (status.equals("ON")) {
//			buzzer.on();
//		} else if (status.equals("OFF")) {
//			buzzer.off();
//		}
//	}
//
//	@Override
//	public void rgbColor(String red, String green, String blue) {
//		int red1 = Integer.parseInt(red);
//		int green1 = Integer.parseInt(green);
//		int blue1 = Integer.parseInt(blue);
//		rgbLed.ledColorSet(red1, green1, blue1);
//	}
}