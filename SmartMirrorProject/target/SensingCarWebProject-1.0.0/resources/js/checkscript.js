function checkScript(message) {
	
	script = message.replace( / /gi ,"");
	
	if(script == "날씨") {
		annyang.pause();
		speakText("오늘 날씨는 자바하기 좋은 날씨입니다.");
		annyang.resume();
	} else if(script == "날씨끄기") {
		annyang.pause();
		speakText("날씨를 종료합니다.");
		annyang.resume();
	} else if(script == "달력") {
		openCalander();
		annyang.pause();
		speakText("달력 정보입니다");
		annyang.resume();
	} else if(script == "달력끄기") {
		annyang.pause();
		speakText("달력을 종료합니다.");
		annyang.resume();
	} else if(script == "카메라") {
		annyang.pause();
		speakText("카메라 정보입니다");
		annyang.resume();
	} else if(script == "카메라끄기") {
		annyang.pause();
		speakText("카메라를 종료합니다.");
		annyang.resume();
	} else if(script == "거울아") {
		screenOn();
		annyang.pause();
		speakText("안녕하세요 무엇을 도와드릴까요?");
		annyang.resume();
	} else if(script == "잘자") {
		screenOff();
		annyang.pause();
		speakText("안녕히계세요");
		annyang.resume();
	}
	
}