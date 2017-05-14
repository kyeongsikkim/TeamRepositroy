package mainDisplay.control;

public class Heat {
    private boolean btnAllSwitch;
    
    private String lblTemp;
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
