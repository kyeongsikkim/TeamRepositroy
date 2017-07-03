package client;

public class SensorValue {

    private static SensorValue sensorValue = new SensorValue();

    private double temperature;
    private double brightness;
    private double distance;
    private double gas;
    private String trackingStatus;

    private String buzzerStatus;
    private String laserStatus;

    private String lcdline0;
    private String lcdline1;

    private int backtireSpeed;
    private String backtireDirection;
    private int fronttireAngle;

    private int cameraLeftRightAngle;
    private int cameraUpDownAngle;

    private int ultrasonicAngle;

    private String rgbStatus;

    private SensorValue() {

    }

    public static SensorValue getSensorValue() {
        return sensorValue;
    }

    public static void setSensorValue(SensorValue sensorValue) {
        SensorValue.sensorValue = sensorValue;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public String getBuzzerStatus() {
        return buzzerStatus;
    }

    public void setBuzzerStatus(String buzzerStatus) {
        this.buzzerStatus = buzzerStatus;
    }

    public String getLaserStatus() {
        return laserStatus;
    }

    public void setLaserStatus(String laserStatus) {
        this.laserStatus = laserStatus;
    }

    public String getLcdline0() {
        return lcdline0;
    }

    public void setLcdline0(String lcdline0) {
        this.lcdline0 = lcdline0;
    }

    public String getLcdline1() {
        return lcdline1;
    }

    public void setLcdline1(String lcdline1) {
        this.lcdline1 = lcdline1;
    }

    public int getBacktireSpeed() {
        return backtireSpeed;
    }

    public void setBacktireSpeed(int backtireSpeed) {
        this.backtireSpeed = backtireSpeed;
    }

    public String getBacktireDirection() {
        return backtireDirection;
    }

    public void setBacktireDirection(String backtireDirection) {
        this.backtireDirection = backtireDirection;
    }

    public int getFronttireAngle() {
        return fronttireAngle;
    }

    public void setFronttireAngle(int fronttireAngle) {
        this.fronttireAngle = fronttireAngle;
    }

    public int getCameraLeftRightAngle() {
        return cameraLeftRightAngle;
    }

    public void setCameraLeftRightAngle(int cameraLeftRightAngle) {
        this.cameraLeftRightAngle = cameraLeftRightAngle;
    }

    public int getCameraUpDownAngle() {
        return cameraUpDownAngle;
    }

    public void setCameraUpDownAngle(int cameraUpDownAngle) {
        this.cameraUpDownAngle = cameraUpDownAngle;
    }

    public int getUltrasonicAngle() {
        return ultrasonicAngle;
    }

    public void setUltrasonicAngle(int ultrasonicAngle) {
        this.ultrasonicAngle = ultrasonicAngle;
    }

    public String getRgbStatus() {
        return rgbStatus;
    }

    public void setRgbStatus(String rgbStatus) {
        this.rgbStatus = rgbStatus;
    }

    public static SensorValue getInstance() {
        return sensorValue;
    }
}
