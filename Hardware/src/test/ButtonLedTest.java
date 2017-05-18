package test;
		
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.button.Button;
import hardware.led.DualColorLed;
import java.io.IOException;

public class ButtonLedTest {
	public static void main(String[] args) throws IOException {
		Button button = new Button(RaspiPin.GPIO_00);
		DualColorLed dualColorLed = new DualColorLed(RaspiPin.GPIO_22, RaspiPin.GPIO_23);
		
		button.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState() == PinState.HIGH) {
					dualColorLed.green();
				} else {
					dualColorLed.red();
				}
			}
		});
		
		System.out.println("ready....");
		System.in.read();
	}
}
