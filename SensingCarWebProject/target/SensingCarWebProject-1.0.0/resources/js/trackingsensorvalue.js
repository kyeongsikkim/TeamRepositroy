$(function() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarWebProject/websocket/trackingsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var tracking = data.tracking;
		
		if(tracking == 1) {
			$("#divTracking").css("background-color", "black");
		} else {
			$("#divTracking").css("background-color", "white");
		}
		
	};
});
