$(function() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControlProject/websocket/photoresistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var brightness = data.photoresistor;
		
		$("#divBrightness").html(brightness);
		
		if (brightness < 80) {
			$("#divPhoto").css("color", "#ffd666");

			$(".wrapper .lightbulb .head").attr("style", "opacity: 1");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 1");

		} else if (80 < brightness && brightness < 90) {
			$("#divPhoto").css("color", "#ffca3a");

			$(".wrapper .lightbulb .head").attr("style", "opacity: 0.9");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 0.9");

		} else if (90 < brightness && brightness < 100) {
			$("#divPhoto").css("color", "#ffc524");

			
			$(".wrapper .lightbulb .head").attr("style", "opacity: 0.8");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 0.8");

		} else if (100 < brightness && brightness < 110) {
			$("#divPhoto").css("color", "#e8b320");

			$(".wrapper .lightbulb .head").attr("style", "opacity: 0.7");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 0.7");

		} else if (110 < brightness && brightness < 120) {
			$("#divPhoto").css("color", "#dda918");

			$(".wrapper .lightbulb .head").attr("style", "opacity: 0.6");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 0.5");

		} else if (120 < brightness && brightness < 130) {
			$("#divPhoto").css("color", "#c19313");
			
			$(".wrapper .lightbulb .head").attr("style", "opacity: 0.5");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 0.3");

		} else {
			$("#divPhoto").css("color", "#9b750a");
			$(".wrapper .lightbulb .head").attr("style", "opacity: 0.4");
			$(".wrapper .lightbulb .body .el__one").attr("style","opacity: 0.1");
		}
	};
});
