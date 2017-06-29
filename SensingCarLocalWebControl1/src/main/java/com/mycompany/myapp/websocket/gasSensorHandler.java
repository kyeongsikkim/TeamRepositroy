package com.mycompany.myapp.websocket;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mycompany.myapp.dto.LcdText;

import hardware.lcd.LCD1602;

@Component
public class gasSensorHandler extends TextWebSocketHandler implements ApplicationListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(gasSensorHandler.class);
	
	private List<WebSocketSession> list = new Vector<>();
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
//	private LCD1602 lcd1602;

	@PostConstruct
	public void init() throws Exception {
		coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.35.46:5683/gassensor");
//		lcd1602 = new LCD1602(0x27);
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			// 매 1초단위로 실행됨.
			public void onLoad(CoapResponse response) {
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				double value = jsonObject.getDouble("gassensor");
//				if(value > 65) {
//					LcdText.getLcd().setText(lcd("gas", "detected"));
//				} else {
//					lcd1602.clear();
//					LcdText.getLcd().setText("");
//				}
				jsonObject = new JSONObject();
				jsonObject.put("time", getTimeUTC(new Date().getTime()));
				jsonObject.put("gassensor",value);
				json = jsonObject.toString();
				try {
					for (WebSocketSession session : list) {
						session.sendMessage(new TextMessage(json));
					}
				} catch (Exception e) {
				}
			}

			@Override
			public void onError() {
				
			}
		});
	}

	// 메소드 이름 = 이 메소드가 언제 시행할지
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
		list.add(session);
	}

	// 연결이 끊어진 후 시행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}

	// 메시지를 받았을 때 시행
	// 여기서 사용하는 session은 HTTPSession이 아님!명심하자!
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
		String strMessage = message.getPayload();
		TextMessage txtMessage = new TextMessage(strMessage);
		session.sendMessage(txtMessage);
	}

	public long getTimeUTC(long localTime) {
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try {
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utcTime;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextClosedEvent) {
			LOGGER.info("Webapplication stop");
			coapObserveRelation.proactiveCancel();
			coapClient.shutdown();
		}
	}
	
	public String lcd(String line0, String line1) {
//		lcd1602.clear();
//		lcd1602.write(0, 0, line0);
//		lcd1602.write(1, 0, line1);
		return line0 + " " + line1;
	}
}