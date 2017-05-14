package mainDisplay.control;

public class Gas {
    private String gasImage;
    private String gasOnOff;

    private boolean btnAllSwitch;
    private String lblOnOff;

    public String getLblOnOff() {
        return lblOnOff;
    }

    public void setLblOnOff(String lblOnOff) {
        this.lblOnOff = lblOnOff;
    }
    
    public boolean isBtnAllSwitch() {
        return btnAllSwitch;
    }

     public boolean getBtnAllSwitch() {
        return btnAllSwitch;
    }
     
    public void setBtnAllSwitch(boolean btnAllSwitch) {
        this.btnAllSwitch = btnAllSwitch;
    }
    
    public String getGasImage() {
        return gasImage;
    }

    public void setGasImage(String gasImage) {
        this.gasImage = gasImage;
    }

    public String getGasOnOff() {
        return gasOnOff;
    }

    public void setGasOnOff(String gasOnOff) {
        this.gasOnOff = gasOnOff;
    }
}
