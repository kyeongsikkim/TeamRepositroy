package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class DualColorLed {
	//디지털 출력 pin 필드
	private GpioPinDigitalOutput redPin;		//전원을 내보내야 하기 때문에 Output
	private GpioPinDigitalOutput greenPin;
	
	//DualColorLed 객체 생성자
	public DualColorLed(Pin redPinNo, Pin greenPinNo) {
		//GpioController 싱글톤 객체 얻기
		GpioController gpioController = GpioFactory.getInstance();		//모든 gpio를 제어, 단 하나의 객체만 있어야 한다
		//디지털 출력 pin 생성
		redPin = gpioController.provisionDigitalOutputPin(redPinNo);
		greenPin = gpioController.provisionDigitalOutputPin(greenPinNo);
		//애플리케이션이 종료될 때 출력 모드를 해제하고, 핀의 출력을 Low
		redPin.setShutdownOptions(true, PinState.LOW);
		greenPin.setShutdownOptions(true, PinState.LOW);
		
	}
	//Method
	public void red() {
		redPin.high();		//프로그레스 속도가 빠르기 때문에 high와 low 순서는 중요하지 않음.
		greenPin.low();
	}
	
	public void green() {
		greenPin.high();
		redPin.low();
	}
	public static void main(String[] args) throws InterruptedException {
		DualColorLed test = new DualColorLed(RaspiPin.GPIO_21, RaspiPin.GPIO_22);
		
		while(true) {
			test.red();
			Thread.sleep(1000);
			test.green();
			Thread.sleep(3000);
		}
	}
}
