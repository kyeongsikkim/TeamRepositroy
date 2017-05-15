
package mainDisplay.setting;

public class Status {
	public static Status sta=new Status();
	//////////////////////////////////////////////////////////////////////////////////////////////
	private String txtSaveMode;
	private double sliderLight=50;
	private double sliderCall=50;
	private double sliderMusic=50;
	private String txtMusic;
	private String imag1;
	private String img2;
	////////////////////////////////////////////////////////////////////////////////////////////
	private double sliderCall1=50;
	private double sliderMusic1=50;
	private String txtMusic1;
	private String imag11;
	private String img21;
	//////////////////////////////////////////////////////////////////////////////////////////
	private double sliderCall2=50;
	private double sliderMusic2=50;
	private String txtMusic2;
	private String imag12;
	private String img22;
	///////////////////////////////////////////////
	private boolean Radionbutton1=true;
	private boolean Radionbutton2;
	private boolean Radionbutton3;
	///////////////////////////////////////////////
	private String txtPass="";
	private boolean checkMotion;
	///////////////Music//////////////////////////
	private String musicAddress1;
	private String musicAddress2;
	private String musicAddress3;
	/////////////////////////////////////////////
	private Status(){
		
	}
	public static Status getInstacne(){
		return sta;
	}
	public String getTxtSaveMode() {
		return txtSaveMode;
	}

	public void setTxtSaveMode(String txtSaveMode) {
		this.txtSaveMode = txtSaveMode;
	}

	public double getSliderLight() {
		return sliderLight;
	}

	public void setSliderLight(double sliderLight) {
		this.sliderLight = sliderLight;
	}

	public double getSliderCall() {
		return sliderCall;
	}

	public void setSliderCall(double sliderCall) {
		this.sliderCall = sliderCall;
	}

	public double getSliderMusic() {
		return sliderMusic;
	}

	public void setSliderMusic(double sliderMusic) {
		this.sliderMusic = sliderMusic;
	}

	public String getTxtMusic() {
		return txtMusic;
	}

	public void setTxtMusic(String txtMusic) {
		this.txtMusic = txtMusic;
	}

	public String getImag1() {
		return imag1;
	}

	public void setImag1(String imag1) {
		this.imag1 = imag1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public double getSliderCall1() {
		return sliderCall1;
	}

	public void setSliderCall1(double sliderCall1) {
		this.sliderCall1 = sliderCall1;
	}

	public double getSliderMusic1() {
		return sliderMusic1;
	}

	public void setSliderMusic1(double sliderMusic1) {
		this.sliderMusic1 = sliderMusic1;
	}

	public String getTxtMusic1() {
		return txtMusic1;
	}

	public void setTxtMusic1(String txtMusic1) {
		this.txtMusic1 = txtMusic1;
	}

	public String getImag11() {
		return imag11;
	}

	public void setImag11(String imag11) {
		this.imag11 = imag11;
	}

	public String getImg21() {
		return img21;
	}

	public void setImg21(String img21) {
		this.img21 = img21;
	}

	public double getSliderCall2() {
		return sliderCall2;
	}

	public void setSliderCall2(double sliderCall2) {
		this.sliderCall2 = sliderCall2;
	}

	public double getSliderMusic2() {
		return sliderMusic2;
	}

	public void setSliderMusic2(double sliderMusic2) {
		this.sliderMusic2 = sliderMusic2;
	}

	public String getTxtMusic2() {
		return txtMusic2;
	}

	public void setTxtMusic2(String txtMusic2) {
		this.txtMusic2 = txtMusic2;
	}

	public String getImag12() {
		return imag12;
	}

	public void setImag12(String imag12) {
		this.imag12 = imag12;
	}

	public String getImg22() {
		return img22;
	}

	public void setImg22(String img22) {
		this.img22 = img22;
	}

	public boolean isRadionbutton1() {
		return Radionbutton1;
	}

	public void setRadionbutton1(boolean Radionbutton1) {
		this.Radionbutton1 = Radionbutton1;
	}

	public boolean isRadionbutton2() {
		return Radionbutton2;
	}

	public void setRadionbutton2(boolean Radionbutton2) {
		this.Radionbutton2 = Radionbutton2;
	}

	public boolean isRadionbutton3() {
		return Radionbutton3;
	}

	public void setRadionbutton3(boolean Radionbutton3) {
		this.Radionbutton3 = Radionbutton3;
	}

	public String getTxtPass() {
		return txtPass;
	}

	public void setTxtPass(String txtPass) {
		if(txtPass.equals("cancle")){
			this.txtPass="";
		}else{
		this.txtPass=txtPass;	
		}
	}

	public boolean isCheckMotion() {
		return checkMotion;
	}

	public void setCheckMotion(boolean checkMotion) {
		this.checkMotion = checkMotion;
	}

	public String getMusicAddress1() {
		return musicAddress1;
	}

	public void setMusicAddress1(String musicAddress1) {
		this.musicAddress1 = musicAddress1;
	}

	public String getMusicAddress2() {
		return musicAddress2;
	}

	public void setMusicAddress2(String musicAddress2) {
		this.musicAddress2 = musicAddress2;
	}

	public String getMusicAddress3() {
		return musicAddress3;
	}

	public void setMusicAddress3(String musicAddress3) {
		this.musicAddress3 = musicAddress3;
	}
	
	
	
	
	
	
	
	
	
}