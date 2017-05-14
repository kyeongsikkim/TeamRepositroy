package mainDisplay.control;

public class Heat {
    private boolean btnAllSwitch;
    
    private String lblTemp;
    private String lblTempLivingRoom;
    private String lblTempRoom1;
    private String lblTempRoom2;
    private String lblTempRoom3;

    public String getLblTempLivingRoom() {
        return lblTempLivingRoom;
    }

    public void setLblTempLivingRoom(String lblTempLivingRoom) {
        this.lblTempLivingRoom = lblTempLivingRoom;
    }

    public String getLblTempRoom1() {
        return lblTempRoom1;
    }

    public void setLblTempRoom1(String lblTempRoom1) {
        this.lblTempRoom1 = lblTempRoom1;
    }

    public String getLblTempRoom2() {
        return lblTempRoom2;
    }

    public void setLblTempRoom2(String lblTempRoom2) {
        this.lblTempRoom2 = lblTempRoom2;
    }

    public String getLblTempRoom3() {
        return lblTempRoom3;
    }

    public void setLblTempRoom3(String lblTempRoom3) {
        this.lblTempRoom3 = lblTempRoom3;
    }
    private String lblTempControl;

    public boolean isBtnAllSwitch() {
        return btnAllSwitch;
    }

    public boolean getBtnAllSwitch() {
        return btnAllSwitch;
    }
    
    public void setBtnAllSwitch(boolean btnAllSwitch) {
        this.btnAllSwitch = btnAllSwitch;
    }

    public String getLblTemp() {
        return lblTemp;
    }

    public void setLblTemp(String lblTemp) {
        this.lblTemp = lblTemp;
    }

    public String getLblTempControl() {
        return lblTempControl;
    }

    public void setLblTempControl(String lblTempControl) {
        this.lblTempControl = lblTempControl;
    }
}
